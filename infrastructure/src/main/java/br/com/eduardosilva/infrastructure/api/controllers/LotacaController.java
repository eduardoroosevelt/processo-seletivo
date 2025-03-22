package br.com.eduardosilva.infrastructure.api.controllers;

import br.com.eduardosilva.application.lotacao.CreateLotacaoUseCase;
import br.com.eduardosilva.infrastructure.api.LotacaoAPI;
import br.com.eduardosilva.infrastructure.endereco.models.CreateEnderecoResponse;
import br.com.eduardosilva.infrastructure.lotacao.models.CreateLotacaoRequest;
import br.com.eduardosilva.infrastructure.lotacao.models.CreateLotacaoResponse;
import br.com.eduardosilva.infrastructure.pessoa.models.CreateServidorEfetivoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;

@RestController
public class LotacaController implements LotacaoAPI {

    private final CreateLotacaoUseCase createLotacaoUseCase;

    public LotacaController(CreateLotacaoUseCase createLotacaoUseCase) {
        this.createLotacaoUseCase = createLotacaoUseCase;

    }

    @Override
    public ResponseEntity<?> createPessoa(CreateLotacaoRequest input) {
        final var res = createLotacaoUseCase.execute(input, CreateLotacaoResponse::new);
        return ResponseEntity.created(URI.create("/lotacao/"+res.lotId()))
                .body(res);
    }
}
