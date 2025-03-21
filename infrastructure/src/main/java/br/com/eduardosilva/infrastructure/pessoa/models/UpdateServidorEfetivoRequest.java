package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.UpdateServidorEfetivoUseCase;

import java.time.LocalDate;
import java.util.Set;

public record UpdateServidorEfetivoRequest(
        Long pesId,
        String pesNome,
        LocalDate pesDataNascimento,
        String pesSexo,
        String pesMae,
        String pesPai,
        String matricula,
        Set<Long> enderecos

) implements UpdateServidorEfetivoUseCase.Input {
}
