package br.com.eduardosilva.infrastructure.api.controllers;

import br.com.eduardosilva.application.servidorEfetivo.CreateServidorEfetivoUseCase;
import br.com.eduardosilva.infrastructure.api.ServidorEfetivoAPI;
import br.com.eduardosilva.infrastructure.endereco.models.CreateEnderecoResponse;
import br.com.eduardosilva.infrastructure.pessoa.models.CreateServidorEfetivoRequest;
import br.com.eduardosilva.infrastructure.pessoa.models.CreateServidorEfetivoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ServidorEfetivoController implements ServidorEfetivoAPI {

    private final CreateServidorEfetivoUseCase createServidorEfetivoUseCase;

    public ServidorEfetivoController(CreateServidorEfetivoUseCase createServidorEfetivoUseCase) {
        this.createServidorEfetivoUseCase = createServidorEfetivoUseCase;
    }

    @Override
    public ResponseEntity<?> create(CreateServidorEfetivoRequest input) {
        final var res = createServidorEfetivoUseCase.execute(input, CreateServidorEfetivoResponse::new);
        return ResponseEntity.created(URI.create("/servidor-efetivos/"+res.id()))
                .body(res);
    }
}
