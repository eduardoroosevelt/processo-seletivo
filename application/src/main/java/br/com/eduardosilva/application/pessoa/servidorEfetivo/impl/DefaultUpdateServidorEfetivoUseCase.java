package br.com.eduardosilva.application.pessoa.servidorEfetivo.impl;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.CreateServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.UpdateServidorEfetivoUseCase;
import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.exceptions.NotFoundException;
import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import br.com.eduardosilva.domain.pessoa.PessoaId;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultUpdateServidorEfetivoUseCase extends UpdateServidorEfetivoUseCase {
    private final PessoaGateway pessoaGateway;
    private final EnderecoGateway enderecoGateway;

    public DefaultUpdateServidorEfetivoUseCase(PessoaGateway pessoaGateway, EnderecoGateway enderecoGateway) {
        this.pessoaGateway = pessoaGateway;
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public Output execute(Input input) {

        final Pessoa aPessoa = this.pessoaGateway.pessoaOfId(new PessoaId(input.pesId()))
                .orElseThrow(() -> NotFoundException.with("Pessoa com id %s não pode ser encontrado".formatted(input.pesId())));

        if(aPessoa.getServidorEfetivo() == null){
            throw DomainException.with("Pessoa não é um servidor efetivo");
        }

        Set<EnderecoID> enderecos=null;
        if(input.enderecos() != null){
            enderecos = input.enderecos().stream().map(EnderecoID::new).collect(Collectors.toSet());
            validateEnderecos(enderecos);
        }


        aPessoa.getServidorEfetivo().updateMatricula(input.matricula());
        aPessoa.updatePesMae(input.pesMae());
        aPessoa.updatePesDataNascimento(input.pesDataNascimento());
        aPessoa.updatePesSexo(input.pesSexo());
        aPessoa.updatePesPai(input.pesPai());
        aPessoa.updatePesNome(input.pesNome());
        aPessoa.updateEnderecos(input.enderecos().stream().map(EnderecoID::new).collect(Collectors.toSet()));
        var  pessoaBD = pessoaGateway.save(aPessoa);
        return new DefaultUpdateServidorEfetivoUseCase.StdOutput(pessoaBD.id());
    }

    record StdOutput(PessoaId pesId) implements UpdateServidorEfetivoUseCase.Output {}

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
