package br.com.eduardosilva.application.unidade.impl;

import br.com.eduardosilva.application.unidade.CreateUnidadeUseCase;
import br.com.eduardosilva.application.unidade.UpdateUnidadeUseCase;
import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.unidade.Unidade;
import br.com.eduardosilva.domain.unidade.UnidadeGateway;
import br.com.eduardosilva.domain.unidade.UnidadeId;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultUpdateUnidadeUseCase extends UpdateUnidadeUseCase {

    private final UnidadeGateway unidadeGateway;
    private final EnderecoGateway enderecoGateway;

    public DefaultUpdateUnidadeUseCase(
            UnidadeGateway unidadeGateway,
            EnderecoGateway enderecoGateway) {

        this.unidadeGateway = unidadeGateway;
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public Output execute(Input input) {
        final var aUnidade = this.unidadeGateway.unidadeOfId(new UnidadeId(input.unidadeId()))
                .orElseThrow(() -> DomainException.with("Unidade com id %s não pode ser encontrado".formatted(input.unidadeId())));

        Set<EnderecoID> enderecos = null;
        enderecos = input.enderecos().stream().map(EnderecoID::new).collect(Collectors.toSet());
        validateEnderecos(enderecos);

        aUnidade
                .updateNome(input.nome())
                .updateSigla(input.sigla())
                .updateEnderecos(enderecos);

        return new StdOutput(unidadeGateway.save(aUnidade));
    }

    record StdOutput(UnidadeId unidadeId) implements UpdateUnidadeUseCase.Output{
        public  StdOutput(Unidade out){
            this(out.id());
        }
    }

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
