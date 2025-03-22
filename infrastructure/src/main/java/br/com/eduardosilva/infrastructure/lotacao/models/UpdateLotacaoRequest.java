package br.com.eduardosilva.infrastructure.lotacao.models;

import br.com.eduardosilva.application.lotacao.UpdateLotacaoUseCase;

import java.time.LocalDate;

public record UpdateLotacaoRequest(
        Long lotId,
        Long pesId,
        Long unidId,
        LocalDate lotDataLotacao,
        LocalDate lotDataRemocao,
        String lotPortaria
) implements UpdateLotacaoUseCase.Input {
}
