package br.com.eduardosilva.application.cidade;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.cidade.CidadePreview;
import br.com.eduardosilva.domain.cidade.CidadeSearchQuery;

public abstract class BuscaCidadePaginadoUseCase
        extends UseCase<CidadeSearchQuery, Pagination<CidadePreview>> {


}
