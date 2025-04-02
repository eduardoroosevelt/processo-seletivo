package br.com.eduardosilva.domain.pessoa;

public record ServidorEfetivoSearchQuery(
        int page,
        int perPage,
        String nome,
        String matricula
) {
}
