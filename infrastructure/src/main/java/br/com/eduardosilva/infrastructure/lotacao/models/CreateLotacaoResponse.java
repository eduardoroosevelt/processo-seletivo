package br.com.eduardosilva.infrastructure.lotacao.models;

import br.com.eduardosilva.application.endereco.CreateEnderecoUseCase;
import br.com.eduardosilva.application.lotacao.CreateLotacaoUseCase;

public record CreateLotacaoResponse(
        Long lotId
) {
    public CreateLotacaoResponse(CreateLotacaoUseCase.Output out){
        this(out.lotId().value());
    }
}
