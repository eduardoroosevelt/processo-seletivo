package br.com.eduardosilva.infrastructure.api.controllers;

import br.com.eduardosilva.application.unidade.BuscarUnidadePaginadoUseCase;
import br.com.eduardosilva.application.unidade.BuscarUnidadePorIdUseCase;
import br.com.eduardosilva.application.unidade.CreateUnidadeUseCase;
import br.com.eduardosilva.application.unidade.UpdateUnidadeUseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.unidade.UnidadePreview;
import br.com.eduardosilva.domain.unidade.UnidadeSearchQuery;
import br.com.eduardosilva.infrastructure.api.UnidadeAPI;
import br.com.eduardosilva.infrastructure.unidade.models.*;
import br.com.eduardosilva.infrastructure.unidade.presenters.UnidadeApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class UnidadeController implements UnidadeAPI {

    private final UpdateUnidadeUseCase updateUnidadeUseCase;
    private final CreateUnidadeUseCase createUnidadeUseCase;
    private final BuscarUnidadePorIdUseCase buscarUnidadePorIdUseCase;
    private final BuscarUnidadePaginadoUseCase buscarUnidadePaginadoUseCase;

    public UnidadeController(UpdateUnidadeUseCase updateUnidadeUseCase,
                             CreateUnidadeUseCase createUnidadeUseCase,
                             BuscarUnidadePorIdUseCase buscarUnidadePorIdUseCase, BuscarUnidadePaginadoUseCase buscarUnidadePaginadoUseCase) {
        this.updateUnidadeUseCase = updateUnidadeUseCase;
        this.createUnidadeUseCase = createUnidadeUseCase;
        this.buscarUnidadePorIdUseCase = buscarUnidadePorIdUseCase;
        this.buscarUnidadePaginadoUseCase = buscarUnidadePaginadoUseCase;
    }

    @Override
    public ResponseEntity<CreateUnidadeResponse> create(CreateUnidadeRequest input) {
        final var res= createUnidadeUseCase.execute(input,CreateUnidadeResponse::new);

        return ResponseEntity.created(URI.create("/videos/"+res.unidadeId()))
                .body(res);
    }

    @Override
    public ResponseEntity<UpdateUnidadeResponse> updateById(Long id, UpdateUnidadeRequest req) {
        if (!Objects.equals(req.unidadeId(), id)) {
            throw DomainException.with("Unidade identifier doesn't matches");
        }

        return ResponseEntity.ok().body(updateUnidadeUseCase.execute(req, UpdateUnidadeResponse::new));
    }

    @Override
    public UnidadeResponse getById(Long id) {
        final var input = new BuscarUnidadePorIdUseCase.Input(){

            @Override
            public Long unidadeId() {
                return id;
            }
        };

        return UnidadeApiPresenter.present(buscarUnidadePorIdUseCase.execute(input));
    }

    @Override
    public Pagination<UnidadePreview> list(int page, int perPage, String nome, String sigla) {
        final var parm = new UnidadeSearchQuery(page, perPage,nome,sigla);
        return buscarUnidadePaginadoUseCase.execute(parm);
    }
}
