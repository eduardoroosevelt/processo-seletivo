package br.com.eduardosilva.infrastructure.cidade.models;

import br.com.eduardosilva.application.cidade.CreateCidadeUseCase;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCidadeRequest(
        @JsonProperty("nome") String nome,
        @JsonProperty("uf") String uf
) implements CreateCidadeUseCase.Input{
}
