package br.com.eduardosilva.domain.pessoa;

import br.com.eduardosilva.domain.unidade.UnidadeId;

public record ServidorEfetivoPorUnidadeIdSearchQuery(
        int page,
        int perPage,
        UnidadeId unidId
) {
}
