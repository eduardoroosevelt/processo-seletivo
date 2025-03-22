package br.com.eduardosilva.infrastructure.lotacao.models;

import br.com.eduardosilva.application.lotacao.UpdateLotacaoUseCase;

public record UpdateLotacaoResponse(Long lotId
) {
    public UpdateLotacaoResponse(UpdateLotacaoUseCase.Output out){
        this(out.lotId().value());
    }
}