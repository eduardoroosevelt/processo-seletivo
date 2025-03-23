package br.com.eduardosilva.application.unidade.impl;

import br.com.eduardosilva.application.unidade.DeleteUnidadeUseCase;
import br.com.eduardosilva.domain.unidade.UnidadeGateway;
import br.com.eduardosilva.domain.unidade.UnidadeId;

public class DefaultDeleteUnidadeUseCase extends DeleteUnidadeUseCase {
    private final UnidadeGateway unidadeGateway;

    public DefaultDeleteUnidadeUseCase(UnidadeGateway unidadeGateway) {
        this.unidadeGateway = unidadeGateway;
    }


    @Override
    public void execute(Long anIn) {
        this.unidadeGateway.delete(new UnidadeId(anIn));
    }
}
