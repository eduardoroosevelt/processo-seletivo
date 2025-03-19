package br.com.eduardosilva.infrastructure.endereco.presenters;

import br.com.eduardosilva.application.endereco.BuscarEnderecoPorIdUseCase;
import br.com.eduardosilva.infrastructure.cidade.models.CidadeResponse;
import br.com.eduardosilva.infrastructure.endereco.models.EnderecoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface EnderecoApiPresenter {
    static EnderecoResponse present(final BuscarEnderecoPorIdUseCase.Output out){
        return new EnderecoResponse(
                out.enderecoId().value(),
                out.endTipoLogradouro(),
                out.endLogradouro(),
                out.endNumero(),
                out.endBairro(),
                new CidadeResponse(
                    out.cidade().id().value(),
                    out.cidade().getNome(),
                    out.cidade().getUf()
                )

        );
    }
}
