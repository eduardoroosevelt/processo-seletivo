package br.com.eduardosilva.infrastructure.pessoa.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ServidoresEfetivosResponse(
        @JsonProperty("nome") String nome,
        @JsonProperty("idade") String idade,
        @JsonProperty("unidadeLotacao") String unidadeLotacao,
        @JsonProperty("fotografia") List<String> fotografia
) {
}
