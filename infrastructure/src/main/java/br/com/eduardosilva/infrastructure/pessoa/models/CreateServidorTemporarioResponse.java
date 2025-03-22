package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.CreateServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.CreateServidorTemporarioUseCase;

public record CreateServidorTemporarioResponse(Long id) {

    public CreateServidorTemporarioResponse(CreateServidorTemporarioUseCase.Output out){
        this(out.pesId().value());
    }
}
