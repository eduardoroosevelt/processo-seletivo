package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.application.servidorEfetivo.CreateServidorEfetivoUseCase;

import java.time.LocalDate;
import java.util.Set;

public record CreateServidorEfetivoRequest(
        String pesNome,
        LocalDate pesDataNascimento,
        String pesSexo,
        String pesMae,
        String pesPai,
        Set<Long> enderecos,
        String matricula
) implements CreateServidorEfetivoUseCase.Input {
}
