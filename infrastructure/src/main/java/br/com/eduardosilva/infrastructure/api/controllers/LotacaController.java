package br.com.eduardosilva.infrastructure.api.controllers;

import br.com.eduardosilva.application.lotacao.BuscarLotacaoPorIdUseCase;
import br.com.eduardosilva.application.lotacao.CreateLotacaoUseCase;
import br.com.eduardosilva.application.lotacao.DeleteLotacaoUseCase;
import br.com.eduardosilva.application.lotacao.UpdateLotacaoUseCase;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.infrastructure.api.LotacaoAPI;
import br.com.eduardosilva.infrastructure.lotacao.models.*;
import br.com.eduardosilva.infrastructure.lotacao.presenters.LotacaoApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class LotacaController implements LotacaoAPI {

    private final CreateLotacaoUseCase createLotacaoUseCase;
    private final UpdateLotacaoUseCase updateLotacaoUseCase;
    private final BuscarLotacaoPorIdUseCase buscarLotacaoPorIdUseCase;
    private final DeleteLotacaoUseCase deleteLotacaoUseCase;

    public LotacaController(
            CreateLotacaoUseCase createLotacaoUseCase,
            UpdateLotacaoUseCase updateLotacaoUseCase, BuscarLotacaoPorIdUseCase buscarLotacaoPorIdUseCase, DeleteLotacaoUseCase deleteLotacaoUseCase
    ) {
        this.createLotacaoUseCase = createLotacaoUseCase;
        this.updateLotacaoUseCase = updateLotacaoUseCase;
        this.buscarLotacaoPorIdUseCase = buscarLotacaoPorIdUseCase;
        this.deleteLotacaoUseCase = deleteLotacaoUseCase;
    }

    @Override
    public ResponseEntity<?> createLotacao(CreateLotacaoRequest input) {
        final var res = createLotacaoUseCase.execute(input, CreateLotacaoResponse::new);
        return ResponseEntity.created(URI.create("/lotacao/"+res.lotId()))
                .body(res);
    }

    @Override
    public ResponseEntity<?> updateById(Long id, UpdateLotacaoRequest req) {
        if (!Objects.equals(req.lotId(), id)) {
            throw DomainException.with("Lotação identificadores não correspondem");
        }
        return ResponseEntity.ok().body(updateLotacaoUseCase.execute(req, UpdateLotacaoResponse::new));

    }

    @Override
    public LotacaoResponse getById(Long id) {
        final var aInput = new BuscarLotacaoPorIdUseCase.Input(){

            @Override
            public Long lotId() {
                return id;
            }
        };
        return LotacaoApiPresenter.present(this.buscarLotacaoPorIdUseCase.execute(aInput));
    }

    @Override
    public void deleteById(Long id) {
        this.deleteLotacaoUseCase.execute(id);
    }

}
