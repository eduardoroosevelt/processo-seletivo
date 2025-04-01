package br.com.eduardosilva.application.unidade.impl;

import br.com.eduardosilva.application.unidade.BuscarUnidadePorIdUseCase;
import br.com.eduardosilva.domain.Entity;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.exceptions.NotFoundException;
import br.com.eduardosilva.domain.unidade.Unidade;
import br.com.eduardosilva.domain.unidade.UnidadeGateway;
import br.com.eduardosilva.domain.unidade.UnidadeId;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultBuscarUnidadePorIdUseCase extends BuscarUnidadePorIdUseCase {

    private final UnidadeGateway unidadeGateway;

    public DefaultBuscarUnidadePorIdUseCase(UnidadeGateway unidadeGateway) {
        this.unidadeGateway = unidadeGateway;
    }


    @Override
    public Output execute(Input input) {
        final var unidadeId = new UnidadeId((input.unidadeId()));
        return this.unidadeGateway
                .unidadeOfId(unidadeId)
                .map(StdOutput::new)
                .orElseThrow(() -> NotFoundException.with("Unidade com id %s não pode ser encontrado".formatted(input.unidadeId())));



    }
    record StdOutput(
            UnidadeId unidadeId,
            String nome,
            String sigla,
            List<Endereco> endereco
    ) implements BuscarUnidadePorIdUseCase.Output{
        public StdOutput(Unidade out){
            this(
                    out.id(),
                    out.getNome(),
                    out.getSigla(),
                    out.getEnderecos()
            );
        }
    }
}
