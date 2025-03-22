package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.application.pessoa.servidorTemporario.UpdateServidorTemporarioUseCase;

import java.time.LocalDate;
import java.util.Set;

public record UpdateServidorTemporarioRequest(
        Long pesId,
        String pesNome,
        LocalDate pesDataNascimento,
        String pesSexo,
        String pesMae,
        String pesPai,
        Set<Long> enderecos,
        LocalDate stDataDemissao,
        LocalDate stDataAdmissao
) implements UpdateServidorTemporarioUseCase.Input {
}
