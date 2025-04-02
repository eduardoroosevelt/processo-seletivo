package br.com.eduardosilva.application.pessoa.servidorTemporario;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.ServidorTemporario;
import br.com.eduardosilva.domain.pessoa.ServidorTemporarioPreview;
import br.com.eduardosilva.domain.pessoa.ServidorTemporarioSearchQuery;

public abstract class BuscarServidorTemporarioPaginadoUseCase extends UseCase<ServidorTemporarioSearchQuery, Pagination<ServidorTemporarioPreview>> {
}
