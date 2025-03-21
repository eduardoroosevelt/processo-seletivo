package br.com.eduardosilva.infrastructure.unidade.models;


import br.com.eduardosilva.application.unidade.CreateUnidadeUseCase;

public record CreateUnidadeResponse(Long unidadeId) {

    public CreateUnidadeResponse(CreateUnidadeUseCase.Output out) {
        this(out.unidadeId().value());
    }
}
