package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.application.servidorEfetivo.CreateServidorEfetivoUseCase;

public record CreateServidorEfetivoResponse(Long id) {

    public CreateServidorEfetivoResponse(CreateServidorEfetivoUseCase.Output out){
        this(out.pesId().value());
    }
}

