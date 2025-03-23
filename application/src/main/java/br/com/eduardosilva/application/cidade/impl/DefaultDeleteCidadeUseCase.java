package br.com.eduardosilva.application.cidade.impl;

import br.com.eduardosilva.application.cidade.DeleteCidadeUseCase;
import br.com.eduardosilva.application.endereco.DeleteEnderecoUseCase;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import br.com.eduardosilva.domain.cidade.CidadeId;

public class DefaultDeleteCidadeUseCase extends DeleteCidadeUseCase {

    private final CidadeGateway cidadeGateway;

    public DefaultDeleteCidadeUseCase(CidadeGateway cidadeGateway) {
        this.cidadeGateway = cidadeGateway;
    }

    @Override
    public void execute(Long anIn) {
        final var cidadeId = new CidadeId(anIn);
        this.cidadeGateway.delete(cidadeId);
    }
}
