package br.com.eduardosilva.domain.unidade;

public record UnidadeSearchQuery(
        int page,
        int perPage,
        String nome,
        String sigla
) {
}
