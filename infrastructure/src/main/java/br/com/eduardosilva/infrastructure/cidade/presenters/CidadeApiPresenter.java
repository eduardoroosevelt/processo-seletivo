package br.com.eduardosilva.infrastructure.cidade.presenters;

import br.com.eduardosilva.application.cidade.BuscarCidadePorIdUseCase;
import br.com.eduardosilva.infrastructure.cidade.models.CidadeResponse;

public interface CidadeApiPresenter {
    static CidadeResponse present(final BuscarCidadePorIdUseCase.Output output){
        return new CidadeResponse(
                output.cidadeId().value(),
                output.nome(),
                output.uf()
        );
    }
}
