package br.com.eduardosilva.domain.pessoa;

import java.time.LocalDate;
import java.util.Set;

public record ServidorEfetivoPorUnidadeIdPreview(
         String nome,
         String unidadeLotacao,
         Integer idade,
         Set<String> fotos
) {
    public ServidorEfetivoPorUnidadeIdPreview(
            String nome,
            String unidadeLotacao,
            LocalDate dataNascimento,
            Set<String> fotos
    ){
        this(
                nome,
                unidadeLotacao,
                LocalDate.now().getYear() - dataNascimento.getYear(),
                fotos);
    }
}