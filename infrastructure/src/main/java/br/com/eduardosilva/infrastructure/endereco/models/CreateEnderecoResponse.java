package br.com.eduardosilva.infrastructure.endereco.models;


import br.com.eduardosilva.application.endereco.CreateEnderecoUseCase;

public record CreateEnderecoResponse(Long enderecoId) {

    public CreateEnderecoResponse(CreateEnderecoUseCase.Output out){
        this(out.enderecoId().value());
    }
}
