package br.com.eduardosilva.application.pessoa.servidorEfetivo;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoPorUnidadeIdPreview;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoPorUnidadeIdSearchQuery;

import java.util.List;
import java.util.Set;

public abstract class BuscarServidorEfetivoPorUnidadeId
        extends UseCase<ServidorEfetivoPorUnidadeIdSearchQuery, Pagination<BuscarServidorEfetivoPorUnidadeId.Output>> {

    public interface Output{
        String nome();
        String unidadeNome();
        Integer idade();
        Set<String> fotos();
    }

}
