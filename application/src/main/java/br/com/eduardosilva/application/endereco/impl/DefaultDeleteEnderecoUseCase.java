package br.com.eduardosilva.application.endereco.impl;

import br.com.eduardosilva.application.endereco.DeleteEnderecoUseCase;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;

public class DefaultDeleteEnderecoUseCase extends DeleteEnderecoUseCase {
    private final EnderecoGateway enderecoGateway;

    public DefaultDeleteEnderecoUseCase(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }


    @Override
    public void execute(Long anIn) {
        final var enderecoId = new EnderecoID(anIn);
        enderecoGateway.delete(enderecoId);
    }
}
