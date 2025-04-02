package br.com.eduardosilva.domain.pessoa;

import java.time.LocalDate;

public record ServidorEfetivoPreview(
        Long pesId,
        String pesNome,
        LocalDate pesDataNascimento,
        String pesSexo,
        String pesMae,
        String pesPai,
        String matricula
) {
}
