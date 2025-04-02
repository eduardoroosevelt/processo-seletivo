package br.com.eduardosilva.application.lotacao;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.lotacao.LotacaoPreview;
import br.com.eduardosilva.domain.lotacao.LotacaoSearchQuery;

public abstract class BuscarLotacaoPaginadoUseCase extends UseCase<LotacaoSearchQuery, Pagination<LotacaoPreview>> {
}
