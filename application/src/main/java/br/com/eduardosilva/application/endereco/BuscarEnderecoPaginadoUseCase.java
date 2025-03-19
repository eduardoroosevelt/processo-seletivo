package br.com.eduardosilva.application.endereco;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.endereco.EnderecoPreview;
import br.com.eduardosilva.domain.endereco.EnderecoSearchQuery;

public abstract class BuscarEnderecoPaginadoUseCase
        extends UseCase<EnderecoSearchQuery, Pagination<EnderecoPreview>> {
}
