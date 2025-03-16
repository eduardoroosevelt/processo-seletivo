package br.com.eduardosilva.application.cidade.impl;

import br.com.eduardosilva.application.cidade.BuscaCidadePaginadoUseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.cidade.*;

public class DefaultBuscaCidadePaginadoUseCase extends BuscaCidadePaginadoUseCase {

    private final CidadeGateway cidadeGateway;

    public DefaultBuscaCidadePaginadoUseCase(CidadeGateway cidadeGateway) {
        this.cidadeGateway = cidadeGateway;
    }

    @Override
    public Pagination<CidadePreview> execute(CidadeSearchQuery input) {
        return this.cidadeGateway.findAll(input);
    }


}
