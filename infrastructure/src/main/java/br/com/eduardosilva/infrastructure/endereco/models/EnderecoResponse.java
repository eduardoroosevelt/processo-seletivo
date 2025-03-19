package br.com.eduardosilva.infrastructure.endereco.models;

import br.com.eduardosilva.infrastructure.cidade.models.CidadeResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public record EnderecoResponse(
        @JsonProperty("enderecoId") Long enderecoId,
        @JsonProperty("endTipoLogradouro") String endTipoLogradouro,
        @JsonProperty("endLogradouro") String endLogradouro,
        @JsonProperty("endNumero") Integer endNumero,
        @JsonProperty("endBairro") String endBairro,
        @JsonProperty("cidade") CidadeResponse cidade

) {
}
