package br.com.eduardosilva.application.lotacao.impl;

import br.com.eduardosilva.application.lotacao.DeleteLotacaoUseCase;
import br.com.eduardosilva.domain.lotacao.LotacaoGateway;
import br.com.eduardosilva.domain.lotacao.LotacaoId;

public class DefaultDeleteLotacaoUseCase extends DeleteLotacaoUseCase {

    private final LotacaoGateway lotacaoGateway;

    public DefaultDeleteLotacaoUseCase(LotacaoGateway lotacaoGateway) {
        this.lotacaoGateway = lotacaoGateway;
    }

    @Override
    public void execute(Long anIn) {
        this.lotacaoGateway.delete(new LotacaoId(anIn));
    }
}
