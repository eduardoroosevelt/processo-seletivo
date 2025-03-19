package br.com.eduardosilva.domain.endereco;

public record EnderecoPreview(
        Long id,
        String endTipoLogradouro,
        String endLogradouro,
        Integer endNumero,
        String endBairro,
        String cidadeNome
) {
}

