package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.application.pessoa.servidorTemporario.UpdateServidorTemporarioUseCase;

public record UpdateServidorTemporarioResponse(Long id) {

    public UpdateServidorTemporarioResponse(UpdateServidorTemporarioUseCase.Output out){
        this(out.pesId().value());
    }
}
