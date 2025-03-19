package br.com.eduardosilva.infrastructure.endereco.models;


import br.com.eduardosilva.application.endereco.UpdateEnderecoUseCase;

public record UpdateEnderecoResponse(Long enderecoId) {
    public UpdateEnderecoResponse(UpdateEnderecoUseCase.Output out){
        this(out.enderecoId().value());
    }
}
