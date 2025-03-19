package br.com.eduardosilva.infrastructure.unidade.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record UnidadeResponse(
        @JsonProperty("unidadeId") Long unidadeId,
        @JsonProperty("nome") String nome,
        @JsonProperty("sigla") String sigla,
        @JsonProperty("enderecos") Set<Long> enderecosId
) {
}
