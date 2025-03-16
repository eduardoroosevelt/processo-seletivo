package br.com.eduardosilva.infrastructure.cidade.models;

import br.com.eduardosilva.application.cidade.CreateCidadeUseCase;

public record CreateCidadeResponse(Long cidadeId) {

    public CreateCidadeResponse(CreateCidadeUseCase.Output out) {
        this(out.cidadeId().value());
    }
}
