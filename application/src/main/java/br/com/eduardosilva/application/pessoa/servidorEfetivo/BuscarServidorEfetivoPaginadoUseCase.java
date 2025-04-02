package br.com.eduardosilva.application.pessoa.servidorEfetivo;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoPreview;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoSearchQuery;

public abstract class BuscarServidorEfetivoPaginadoUseCase extends UseCase<ServidorEfetivoSearchQuery, Pagination<ServidorEfetivoPreview>> {
}
