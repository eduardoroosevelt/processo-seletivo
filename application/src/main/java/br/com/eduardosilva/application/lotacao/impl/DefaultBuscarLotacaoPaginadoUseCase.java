package br.com.eduardosilva.application.lotacao.impl;

import br.com.eduardosilva.application.lotacao.BuscarLotacaoPaginadoUseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.lotacao.LotacaoGateway;
import br.com.eduardosilva.domain.lotacao.LotacaoPreview;
import br.com.eduardosilva.domain.lotacao.LotacaoSearchQuery;

public class DefaultBuscarLotacaoPaginadoUseCase extends BuscarLotacaoPaginadoUseCase {

    private final LotacaoGateway lotacaoGateway;

    public DefaultBuscarLotacaoPaginadoUseCase(LotacaoGateway lotacaoGateway) {
        this.lotacaoGateway = lotacaoGateway;
    }

    @Override
    public Pagination<LotacaoPreview> execute(LotacaoSearchQuery lotacaoSearchQuery) {
        return this.lotacaoGateway.findAll(lotacaoSearchQuery);
    }
}
