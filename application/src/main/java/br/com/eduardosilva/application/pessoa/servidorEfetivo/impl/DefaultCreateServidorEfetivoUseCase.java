package br.com.eduardosilva.application.pessoa.servidorEfetivo.impl;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.CreateServidorEfetivoUseCase;
import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.pessoa.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultCreateServidorEfetivoUseCase extends CreateServidorEfetivoUseCase {

    private final PessoaGateway servidorEfetivoGateway;
    private final EnderecoGateway enderecoGateway;

    public DefaultCreateServidorEfetivoUseCase(
            PessoaGateway servidorEfetivoGateway,
            EnderecoGateway enderecoGateway
    ) {
        this.servidorEfetivoGateway = servidorEfetivoGateway;
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public Output execute(Input input) {
        final Optional<Pessoa> opPessoa=servidorEfetivoGateway.existePessoa(
                input.pesNome(),
                input.pesPai(),
                input.pesMae(),
                input.pesDataNascimento()
        );

        Set<EnderecoID> enderecos=null;

        if(input.enderecos() != null){
            enderecos = input.enderecos().stream().map(EnderecoID::new).collect(Collectors.toSet());
            validateEnderecos(enderecos);
        }

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

            if(enderecos!= null){
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
