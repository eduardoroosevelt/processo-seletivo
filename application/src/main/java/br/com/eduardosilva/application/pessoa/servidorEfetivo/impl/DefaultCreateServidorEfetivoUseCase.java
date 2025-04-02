package br.com.eduardosilva.application.pessoa.servidorEfetivo.impl;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.CreateServidorEfetivoUseCase;
import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.pessoa.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultCreateServidorEfetivoUseCase extends CreateServidorEfetivoUseCase {

    private final PessoaGateway servidorEfetivoGateway;
    private final CidadeGateway cidadeGateway;

    public DefaultCreateServidorEfetivoUseCase(
            PessoaGateway servidorEfetivoGateway,
            EnderecoGateway enderecoGateway, CidadeGateway cidadeGateway
    ) {
        this.servidorEfetivoGateway = servidorEfetivoGateway;
        this.cidadeGateway = cidadeGateway;
    }

    @Override
    public Output execute(Input input) {
        final Optional<Pessoa> opPessoa=servidorEfetivoGateway.existePessoa(
                input.pesNome(),
                input.pesPai(),
                input.pesMae(),
                input.pesDataNascimento()
        );

        servidorEfetivoGateway.findByServidorEfetivoMatricula(input.matricula())
                .ifPresent(p -> {
                    throw DomainException.with("Matrícula %s já cadastrada para a pessoa %s".formatted(input.matricula(), p.getPesNome()));
                });

        List<Endereco> enderecos= input.enderecos().stream().map(er -> {
                Cidade cidade = cidadeGateway.cidadeOfId(new CidadeId(er.cidadeId()))
                        .orElseThrow(() -> DomainException.with("Cidade com id %s não pode ser encontrado".formatted(er.cidadeId())));

                return new Endereco(
                EnderecoID.empty(),
                er.endTipoLogradouro(),
                er.endLogradouro(),
                er.endNumero(),
                er.endBairro(),
                cidade);

        }).collect(Collectors.toList());

        ServidorEfetivo servidorEfetivo = new ServidorEfetivo(input.matricula());
        Pessoa pessoa;

        if(opPessoa.isPresent()){
            if(opPessoa.get().getServidorEfetivo() != null){
                throw DomainException.with("Pessoa já cadastrada como servidor efetivo ");
            }

            if(opPessoa.get().getServidorTemporario() != null && opPessoa.get().getServidorTemporario().getStDataDemissao() == null){
                throw DomainException.with("Pessoa é um servidor temporário que não tem data de demissão. Favor preencher a data de demissão primeiro. ");
            }
            pessoa = opPessoa.get();

            if(!enderecos.isEmpty()){
                enderecos.addAll(pessoa.getEnderecos());
                pessoa.updateEnderecos(enderecos);
            }
            pessoa.updateServidorEfetivo(servidorEfetivo);

        }else{
             pessoa = new Pessoa(
                    servidorEfetivoGateway.nextId(),
                    input.pesNome(),
                    input.pesDataNascimento(),
                    input.pesSexo(),
                    input.pesMae(),
                    input.pesPai(),
                     enderecos,
                     null
            );
            pessoa.updateServidorEfetivo(servidorEfetivo);
        }

        var  pessoaBD = servidorEfetivoGateway.save(pessoa);
        return new DefaultCreateServidorEfetivoUseCase.StdOutput(pessoaBD.id());
    }

    record StdOutput(PessoaId pesId) implements CreateServidorEfetivoUseCase.Output {}

    private void validateCidade(final Set<CidadeId> ids){
        if (ids == null || ids.isEmpty()) {
            return ;
        }

        final var retrievedIds = cidadeGateway.existsByIds(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrievedIds);

            final var missingIdsMessage = missingIds.stream()
                    .map(Identifier::value)
                    .map(id ->id.toString())
                    .collect(Collectors.joining(", "));

            throw DomainException.with("Algumas cidades não pode ser encontrado: %s".formatted(missingIdsMessage) );
        }
    }



}
