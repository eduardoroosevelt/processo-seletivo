package br.com.eduardosilva.application.pessoa.servidorTemporario.impl;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.CreateServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.impl.DefaultCreateServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.CreateServidorTemporarioUseCase;
import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import br.com.eduardosilva.domain.pessoa.PessoaId;
import br.com.eduardosilva.domain.pessoa.ServidorTemporario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultCreateServidorTemporarioUseCase extends CreateServidorTemporarioUseCase {

    private final PessoaGateway pessoaGateway;
    private final EnderecoGateway enderecoGateway;
    private final CidadeGateway cidadeGateway;

    public DefaultCreateServidorTemporarioUseCase(PessoaGateway pessoaGateway, EnderecoGateway enderecoGateway, CidadeGateway cidadeGateway) {
        this.pessoaGateway = pessoaGateway;
        this.enderecoGateway = enderecoGateway;
        this.cidadeGateway = cidadeGateway;
    }

    @Override
    public Output execute(Input input) {

        final Optional<Pessoa> opPessoa=pessoaGateway.existePessoa(
                input.pesNome(),
                input.pesPai(),
                input.pesMae(),
                input.pesDataNascimento()
        );


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

        ServidorTemporario servidorTemp = new ServidorTemporario(input.stDataAdmissao(),input.stDataDemissao());
        Pessoa pessoa;
        if(opPessoa.isPresent()){
            if(opPessoa.get().getServidorTemporario() != null){
                throw DomainException.with("Pessoa já cadastrada como servidor temporario. ");
            }
            if(opPessoa.get().getServidorEfetivo()!= null){
                throw DomainException.with("Pessoa está cadastrada como servidor efetivo. ");
            }
            pessoa = opPessoa.get();
            pessoa.updateServidorTemporario(servidorTemp);

            if(!enderecos.isEmpty()){
                enderecos.addAll(pessoa.getEnderecos());
                pessoa.updateEnderecos(enderecos);
            }

        }else{
            pessoa = new Pessoa(
                    pessoaGateway.nextId(),
                    input.pesNome(),
                    input.pesDataNascimento(),
                    input.pesSexo(),
                    input.pesMae(),
                    input.pesPai(),
                    enderecos,
                    null
            );
            pessoa.updateServidorTemporario(servidorTemp);
        }

        var  pessoaBD = pessoaGateway.save(pessoa);
        return new DefaultCreateServidorTemporarioUseCase.StdOutput(pessoaBD.id());
    }

    record StdOutput(PessoaId pesId) implements CreateServidorTemporarioUseCase.Output {}

    private void validateEnderecos(final Set<EnderecoID> ids){
        if (ids == null || ids.isEmpty()) {
            return ;
        }

        final var retrievedIds = enderecoGateway.existsByIds(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrievedIds);

            final var missingIdsMessage = missingIds.stream()
                    .map(Identifier::value)
                    .map(id ->id.toString())
                    .collect(Collectors.joining(", "));

            throw DomainException.with("Alguns Endereços não pode ser encontrado: %s".formatted(missingIdsMessage) );
        }
    }
}
