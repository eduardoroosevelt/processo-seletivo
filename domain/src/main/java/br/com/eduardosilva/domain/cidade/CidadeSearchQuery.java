package br.com.eduardosilva.domain.cidade;

public record CidadeSearchQuery(
        int page,
        int perPage,
        String nome,
        String uf
) {
}
