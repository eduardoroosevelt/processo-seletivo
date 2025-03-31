package br.com.eduardosilva.infrastructure.unidade.models;

import br.com.eduardosilva.application.CreateEnderecoCommand;
import br.com.eduardosilva.application.unidade.CreateUnidadeUseCase;

import java.util.List;
import java.util.Set;

public record CreateUnidadeRequest(
        String nome,
        String sigla,
        List<CreateEnderecoCommand> enderecos
) implements CreateUnidadeUseCase.Input {
}
