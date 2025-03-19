package br.com.eduardosilva.application.unidade.impl;

import br.com.eduardosilva.application.endereco.BuscarEnderecoPaginadoUseCase;
import br.com.eduardosilva.application.unidade.BuscarUnidadePaginadoUseCase;
import br.com.eduardosilva.application.unidade.BuscarUnidadePorIdUseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.unidade.UnidadeGateway;
import br.com.eduardosilva.domain.unidade.UnidadePreview;
import br.com.eduardosilva.domain.unidade.UnidadeSearchQuery;

public class DefaultBuscarUnidadePaginadoUseCase extends BuscarUnidadePaginadoUseCase {
    private final UnidadeGateway unidadeGateway;

    public DefaultBuscarUnidadePaginadoUseCase(UnidadeGateway unidadeGateway) {
        this.unidadeGateway = unidadeGateway;
    }


    @Override
    public Pagination<UnidadePreview> execute(UnidadeSearchQuery unidadeSearchQuery) {
        return this.unidadeGateway.findAll(unidadeSearchQuery);
    }
}
