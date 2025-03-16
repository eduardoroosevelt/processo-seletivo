package br.com.eduardosilva.infrastructure.cidade.models;

import br.com.eduardosilva.application.cidade.UpdateCidadeUseCase;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCidadeRequest(
        @JsonProperty("cidadeId") Long cidadeId,
        @JsonProperty("nome") String nome,
        @JsonProperty("uf") String uf
) implements UpdateCidadeUseCase.Input {
}
