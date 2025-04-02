package br.com.eduardosilva.infrastructure.api.controllers;

import br.com.eduardosilva.application.pessoa.BuscarPessoaPorIdUseCase;
import br.com.eduardosilva.application.pessoa.UploadFotoUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.BuscarServidorTemporarioPaginadoUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.CreateServidorTemporarioUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.DeleteServidorTemporarioUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.UpdateServidorTemporarioUseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.pessoa.ServidorTemporarioPreview;
import br.com.eduardosilva.domain.pessoa.ServidorTemporarioSearchQuery;
import br.com.eduardosilva.domain.shared.Resource;
import br.com.eduardosilva.infrastructure.api.ServidorTemporarioAPI;
import br.com.eduardosilva.infrastructure.pessoa.models.*;
import br.com.eduardosilva.infrastructure.pessoa.presenters.PessoaApiPresenter;
import br.com.eduardosilva.infrastructure.util.HashingUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ServidorTemporarioController implements ServidorTemporarioAPI {

    private final CreateServidorTemporarioUseCase createServidorTemporarioUseCase;
    private final UpdateServidorTemporarioUseCase updateServidorTemporarioUseCase;
    private final BuscarPessoaPorIdUseCase buscarPessoaPorIdUseCase;
    private final DeleteServidorTemporarioUseCase deleteServidorTemporarioUseCase;
    private final UploadFotoUseCase uploadFotoUseCase;
    private final BuscarServidorTemporarioPaginadoUseCase buscarServidorTemporarioPaginadoUseCase;

    public ServidorTemporarioController(CreateServidorTemporarioUseCase createServidorTemporarioUseCase,
                                        UpdateServidorTemporarioUseCase updateServidorTemporarioUseCase,
                                        BuscarPessoaPorIdUseCase buscarPessoaPorIdUseCase,
                                        DeleteServidorTemporarioUseCase deleteServidorTemporarioUseCase,
                                        UploadFotoUseCase uploadFotoUseCase,
                                        BuscarServidorTemporarioPaginadoUseCase buscarServidorTemporarioPaginadoUseCase) {

        this.createServidorTemporarioUseCase = createServidorTemporarioUseCase;
        this.updateServidorTemporarioUseCase = updateServidorTemporarioUseCase;
        this.buscarPessoaPorIdUseCase = buscarPessoaPorIdUseCase;
        this.deleteServidorTemporarioUseCase = deleteServidorTemporarioUseCase;
        this.uploadFotoUseCase = uploadFotoUseCase;
        this.buscarServidorTemporarioPaginadoUseCase = buscarServidorTemporarioPaginadoUseCase;
    }

    @Override
    public ResponseEntity<?> createServidorTemporario(CreateServidorTemporarioRequest input) {
        final var res = createServidorTemporarioUseCase.execute(input, CreateServidorTemporarioResponse::new);
        return ResponseEntity.created(URI.create("/servidor-efetivos/"+res.id()))
                .body(res);
    }

    @Override
    public ResponseEntity<?> updateByTemporarioId(Long id, UpdateServidorTemporarioRequest req) {
        if (!Objects.equals(req.pesId(), id)) {
            throw DomainException.with("Serviço Temporario identifier doesn't matches");
        }
        return  ResponseEntity.ok().body(updateServidorTemporarioUseCase.execute(req, UpdateServidorTemporarioResponse::new));
    }

    @Override
    public BuscarPessoaPorIdResponse getById(Long id) {
        final var aInput = new BuscarPessoaPorIdUseCase.Input(){
            @Override
            public Long pesId() {
                return id;
            }
        };

        return PessoaApiPresenter.present(this.buscarPessoaPorIdUseCase.execute(aInput));
    }

    @Override
    public void deleteById(Long id) {
        deleteServidorTemporarioUseCase.execute(id);
    }


    @Override
    public ResponseEntity<?> upload(Long pesId, List<MultipartFile> fotos) {
        Set<Resource> fotosResource = fotos.stream().map(this::resourceOf).collect(Collectors.toSet());
        final var input = new UploadFotoUseCase.Input(){

            @Override
            public Long pesId() {
                return pesId;
            }

            @Override
            public Set<Resource> fotos() {
                return fotosResource;
            }
        };
        final var links = uploadFotoUseCase.execute(input);
        return  ResponseEntity.ok().body(links);
    }

    @Override
    public Pagination<ServidorTemporarioPreview> list(int page, int perPage, LocalDate stDataAdmissao, LocalDate stDataDemissao, String nome) {
        final var parm = new ServidorTemporarioSearchQuery(page,perPage,stDataAdmissao,stDataDemissao,nome);
        return buscarServidorTemporarioPaginadoUseCase.execute(parm);
    }


    private Resource resourceOf(final MultipartFile part) {
        if (part == null) {
            return null;
        }
        try {
            return Resource.with(
                    part.getBytes(),
                    HashingUtils.checksum(part.getBytes()),
                    part.getContentType(),
                    part.getOriginalFilename()
            );
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
