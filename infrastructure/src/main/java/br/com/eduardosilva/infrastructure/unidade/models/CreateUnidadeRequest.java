package br.com.eduardosilva.infrastructure.unidade.models;

import br.com.eduardosilva.application.unidade.CreateUnidadeUseCase;

import java.util.Set;

public record CreateUnidadeRequest(
        String nome,
        String sigla,
        Set<Long> enderecos
) implements CreateUnidadeUseCase.Input {
}
