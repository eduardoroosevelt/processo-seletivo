package br.com.eduardosilva.infrastructure.unidade.models;

import br.com.eduardosilva.application.unidade.UpdateUnidadeUseCase;

import java.util.Set;

public record UpdateUnidadeRequest(
        Long unidadeId,
        String nome,
        String sigla,
        Set<Long> enderecos
) implements UpdateUnidadeUseCase.Input {
}
