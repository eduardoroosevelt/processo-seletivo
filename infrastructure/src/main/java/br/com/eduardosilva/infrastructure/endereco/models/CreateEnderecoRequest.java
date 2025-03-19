package br.com.eduardosilva.infrastructure.endereco.models;

import br.com.eduardosilva.application.endereco.CreateEnderecoUseCase;

public record CreateEnderecoRequest(
        String endTipoLogradouro,
        String endLogradouro,
        Integer endNumero,
        String endBairro,
        Long cidadeId

) implements CreateEnderecoUseCase.Input {
}
