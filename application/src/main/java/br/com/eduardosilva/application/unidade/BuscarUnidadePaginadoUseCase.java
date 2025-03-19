package br.com.eduardosilva.application.unidade;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.unidade.UnidadePreview;
import br.com.eduardosilva.domain.unidade.UnidadeSearchQuery;

public abstract class BuscarUnidadePaginadoUseCase  extends UseCase<UnidadeSearchQuery, Pagination<UnidadePreview>> {
}
