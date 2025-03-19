package br.com.eduardosilva.infrastructure.cidade.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CidadeResponse(
        @JsonProperty("cidadeId") Long cidadeId,
        @JsonProperty("nome") String nome,
        @JsonProperty("uf") String uf
)   {
}
