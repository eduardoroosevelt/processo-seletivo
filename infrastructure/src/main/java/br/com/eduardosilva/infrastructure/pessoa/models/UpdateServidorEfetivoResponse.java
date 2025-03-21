package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.application.endereco.UpdateEnderecoUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.UpdateServidorEfetivoUseCase;

public record UpdateServidorEfetivoResponse(Long pesId) {
    public UpdateServidorEfetivoResponse(UpdateServidorEfetivoUseCase.Output out){
        this(out.pesId().value());
    }
}
