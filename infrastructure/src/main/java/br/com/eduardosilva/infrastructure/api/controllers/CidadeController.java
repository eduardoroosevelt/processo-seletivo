package br.com.eduardosilva.infrastructure.api.controllers;

import br.com.eduardosilva.application.cidade.CreateCidadeUseCase;
import br.com.eduardosilva.application.cidade.UpdateCidadeUseCase;
import br.com.eduardosilva.infrastructure.api.CidadeAPI;
import br.com.eduardosilva.infrastructure.cidade.models.CreateCidadeRequest;
import br.com.eduardosilva.infrastructure.cidade.models.CreateCidadeResponse;
import br.com.eduardosilva.infrastructure.cidade.models.UpdateCidadeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CidadeController implements CidadeAPI {
    private final CreateCidadeUseCase createCidadeUseCase;
    private final UpdateCidadeUseCase updateCidadeUseCase;

    public CidadeController(CreateCidadeUseCase createCidadeUseCase,
                            UpdateCidadeUseCase updateCidadeUseCase) {
        this.createCidadeUseCase = createCidadeUseCase;
        this.updateCidadeUseCase = updateCidadeUseCase;
    }


    @Override
    public ResponseEntity<CreateCidadeResponse> create(CreateCidadeRequest input) {
        final var res = createCidadeUseCase.execute(input, CreateCidadeResponse::new);
        return ResponseEntity.created(URI.create("/cidades/"+res.cidadeId()))
                .body(res);
    }

    @Override
    public ResponseEntity<?> updateById(String id, UpdateCidadeRequest input) {
        return null;
    }
}
