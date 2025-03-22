package br.com.eduardosilva.domain.lotacao;

import br.com.eduardosilva.domain.unidade.UnidadeId;

public record LotacaoSearchQuery(
        int page,
        int perPage,
        String lotPortaria,
        UnidadeId unidadeId
) {
}
