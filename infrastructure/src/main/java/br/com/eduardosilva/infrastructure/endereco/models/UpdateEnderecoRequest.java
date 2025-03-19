package br.com.eduardosilva.infrastructure.endereco.models;

import br.com.eduardosilva.application.endereco.UpdateEnderecoUseCase;

public record UpdateEnderecoRequest(
        Long enderecoId,
        String endTipoLogradouro,
        String endLogradouro,
        Integer endNumero,
        String endBairro,
        Long cidadeId
) implements UpdateEnderecoUseCase.Input {
}
