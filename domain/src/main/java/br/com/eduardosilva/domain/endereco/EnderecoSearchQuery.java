package br.com.eduardosilva.domain.endereco;

import br.com.eduardosilva.domain.cidade.CidadeId;

public record EnderecoSearchQuery(
        int page,
        int perPage,
        String endTipoLogradouro,
        String endLogradouro,
        Integer endNumero,
        String endBairro,
        CidadeId cidadeId
) {
}
