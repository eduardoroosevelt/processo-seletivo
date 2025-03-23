package br.com.eduardosilva.infrastructure.api.controllers;

import br.com.eduardosilva.application.cidade.*;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.cidade.CidadePreview;
import br.com.eduardosilva.domain.cidade.CidadeSearchQuery;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.infrastructure.api.CidadeAPI;
import br.com.eduardosilva.infrastructure.cidade.models.*;
import br.com.eduardosilva.infrastructure.cidade.presenters.CidadeApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class CidadeController implements CidadeAPI {
    private final CreateCidadeUseCase createCidadeUseCase;
    private final UpdateCidadeUseCase updateCidadeUseCase;
    private final BuscaCidadePaginadoUseCase buscaCidadePaginadoUseCase;
    private final BuscarCidadePorIdUseCase buscarCidadePorIdUseCase;
    private final DeleteCidadeUseCase deleteCidadeUseCase;

    public CidadeController(CreateCidadeUseCase createCidadeUseCase,
                            UpdateCidadeUseCase updateCidadeUseCase, BuscaCidadePaginadoUseCase buscaCidadePaginadoUseCase, BuscarCidadePorIdUseCase buscarCidadePorIdUseCase, DeleteCidadeUseCase deleteCidadeUseCase) {
        this.createCidadeUseCase = createCidadeUseCase;
        this.updateCidadeUseCase = updateCidadeUseCase;
        this.buscaCidadePaginadoUseCase = buscaCidadePaginadoUseCase;
        this.buscarCidadePorIdUseCase = buscarCidadePorIdUseCase;
        this.deleteCidadeUseCase = deleteCidadeUseCase;
    }

    @Override
    public ResponseEntity<CreateCidadeResponse> create(CreateCidadeRequest input) {
        final var res = createCidadeUseCase.execute(input, CreateCidadeResponse::new);
        return ResponseEntity.created(URI.create("/cidades/"+res.cidadeId()))
                .body(res);
    }

    @Override
    public ResponseEntity<UpdateCidadeResponse> updateById(Long id, UpdateCidadeRequest req) {
        if (!Objects.equals(req.cidadeId(), id)) {
            throw DomainException.with("Cidade identifier doesn't matches");
        }
        return ResponseEntity.ok().body(updateCidadeUseCase.execute(req,UpdateCidadeResponse::new));
    }

    @Override
    public Pagination<CidadePreview> list(int page, int perPage, String uf, String nome) {
        final var parm = new CidadeSearchQuery(page,perPage,nome,uf);
        return buscaCidadePaginadoUseCase.execute(parm);
    }

    @Override
    public CidadeResponse getById(Long id) {
        final var aInput = new BuscarCidadePorIdUseCase.Input(){

            @Override
            public Long cidadeId() {
                return id;
            }
        };

        final var a=  CidadeApiPresenter.present(buscarCidadePorIdUseCase.execute(aInput));
        return a;
    }

    @Override
    public void deleteById(Long id) {
        this.deleteCidadeUseCase.execute(id);
    }
}
