package br.com.eduardosilva.application;

public record UpdateEnderecoCommand(
        Long endId,
        String endTipoLogradouro,
        String endLogradouro,
        Integer endNumero,
        String endBairro,
        Long cidadeId
) {
}
