package br.com.eduardosilva.infrastructure.cidade.models;

import br.com.eduardosilva.application.cidade.UpdateCidadeUseCase;
import br.com.eduardosilva.domain.cidade.CidadeId;

public record UpdateCidadeResponse(Long cidadeId)  {

    public UpdateCidadeResponse(UpdateCidadeUseCase.Output out){
        this(out.cidadeId().value());
    }
}
