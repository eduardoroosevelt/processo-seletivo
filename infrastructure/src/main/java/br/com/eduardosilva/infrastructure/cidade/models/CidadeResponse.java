package br.com.eduardosilva.infrastructure.cidade.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CidadeResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("nome") String nome,
        @JsonProperty("uf") String uf
)   {
}
