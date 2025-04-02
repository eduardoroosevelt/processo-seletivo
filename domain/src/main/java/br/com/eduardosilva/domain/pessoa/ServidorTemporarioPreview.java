package br.com.eduardosilva.domain.pessoa;

import java.time.LocalDate;

public record  ServidorTemporarioPreview(
        Long pesId,
         String pesNome,
         LocalDate pesDataNascimento,
         String pesSexo,
         String pesMae,
         String pesPai,
         LocalDate stDataAdmissao,
        LocalDate stDataDemissao
) {
}
