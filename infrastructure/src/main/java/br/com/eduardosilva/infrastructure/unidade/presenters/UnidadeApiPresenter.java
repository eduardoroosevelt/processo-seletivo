package br.com.eduardosilva.infrastructure.unidade.presenters;


import br.com.eduardosilva.application.unidade.BuscarUnidadePorIdUseCase;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.infrastructure.unidade.models.UnidadeResponse;

import java.util.stream.Collectors;

public interface UnidadeApiPresenter {

    static UnidadeResponse present(final BuscarUnidadePorIdUseCase.Output out){

        return new UnidadeResponse(
                out.unidadeId().value(),
                out.nome(),
                out.sigla(),
                out.endereco().stream().map(EnderecoID::value).collect(Collectors.toSet())
        );
    }
}
