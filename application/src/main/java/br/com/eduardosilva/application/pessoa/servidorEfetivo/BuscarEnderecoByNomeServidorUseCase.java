package br.com.eduardosilva.application.pessoa.servidorEfetivo;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.EnderecoFuncionalPorNomeServidorPreview;
import br.com.eduardosilva.domain.pessoa.EnderecoFuncionalPorNomeServidorSearch;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoPorUnidadeIdPreview;

public abstract class BuscarEnderecoByNomeServidorUseCase extends UseCase<EnderecoFuncionalPorNomeServidorSearch, Pagination<EnderecoFuncionalPorNomeServidorPreview>> {
}
