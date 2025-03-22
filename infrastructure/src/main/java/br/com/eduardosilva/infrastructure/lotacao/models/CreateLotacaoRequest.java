package br.com.eduardosilva.infrastructure.lotacao.models;

import br.com.eduardosilva.application.endereco.CreateEnderecoUseCase;
import br.com.eduardosilva.application.lotacao.CreateLotacaoUseCase;

import java.time.LocalDate;

public record CreateLotacaoRequest(
        Long pesId,
        Long unidId,
        LocalDate lotDataLotacao,
        LocalDate lotDataRemocao,
        String lotPortaria
) implements CreateLotacaoUseCase.Input {


}
