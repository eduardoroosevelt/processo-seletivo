package br.com.eduardosilva.infrastructure.unidade.models;

import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.infrastructure.endereco.models.EnderecoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

public record UnidadeResponse(
        @JsonProperty("unidadeId") Long unidadeId,
        @JsonProperty("nome") String nome,
        @JsonProperty("sigla") String sigla,
        @JsonProperty("enderecos") List<EnderecoResponse> enderecosId
) {
}
