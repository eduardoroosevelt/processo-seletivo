package br.com.eduardosilva.infrastructure.unidade.models;

import br.com.eduardosilva.application.UpdateEnderecoCommand;
import br.com.eduardosilva.application.unidade.UpdateUnidadeUseCase;

import java.util.List;
import java.util.Set;

public record UpdateUnidadeRequest(
        Long unidadeId,
        String nome,
        String sigla,
        List<UpdateEnderecoCommand> enderecos
) implements UpdateUnidadeUseCase.Input {
}
