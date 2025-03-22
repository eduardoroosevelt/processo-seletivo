package br.com.eduardosilva.infrastructure.lotacao.presenters;


import br.com.eduardosilva.application.lotacao.BuscarLotacaoPorIdUseCase;
import br.com.eduardosilva.infrastructure.lotacao.models.LotacaoResponse;

import java.time.LocalDate;

public interface LotacaoApiPresenter {

    static LotacaoResponse present(final BuscarLotacaoPorIdUseCase.Output out){
        return new LotacaoResponse(
                out.lotId(),
                out.nomePessoa(),
                out.nomeUnidade(),
                out.siglaUnidade(),
                out.lotDataLotacao(),
                out.lotDataRemocao(),
                out.lotPortaria()
        );
    }
}
