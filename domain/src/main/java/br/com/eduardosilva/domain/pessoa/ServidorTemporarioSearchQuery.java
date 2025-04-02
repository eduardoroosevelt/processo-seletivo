package br.com.eduardosilva.domain.pessoa;

import java.time.LocalDate;

public record ServidorTemporarioSearchQuery(
        int page,
        int perPage,
        LocalDate stDataAdmissao,
        LocalDate stDataDemissao,
        String nome
) {
}
