package br.com.eduardosilva.infrastructure.api.controllers;

import br.com.eduardosilva.application.pessoa.BuscarPessoaPorIdUseCase;
import br.com.eduardosilva.application.pessoa.UploadFotoUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.CreateServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.DeleteServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.UpdateServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.CreateServidorTemporarioUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.UpdateServidorTemporarioUseCase;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.shared.Resource;
import br.com.eduardosilva.infrastructure.api.ServidorEfetivoAPI;
import br.com.eduardosilva.infrastructure.pessoa.models.*;
import br.com.eduardosilva.infrastructure.pessoa.presenters.PessoaApiPresenter;
import br.com.eduardosilva.infrastructure.util.HashingUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ServidorEfetivoController implements ServidorEfetivoAPI {

    private final CreateServidorEfetivoUseCase createServidorEfetivoUseCase;
    private final UploadFotoUseCase uploadFotoUseCase;
    private final UpdateServidorEfetivoUseCase updateServidorEfetivoUseCase;
    private final BuscarPessoaPorIdUseCase buscarPessoaPorIdUseCase;
    private final DeleteServidorEfetivoUseCase deleteServidorEfetivoUseCase;


    public ServidorEfetivoController(
            CreateServidorEfetivoUseCase createServidorEfetivoUseCase,
            UploadFotoUseCase uploadFotoUseCase,
            UpdateServidorEfetivoUseCase updateServidorEfetivoUseCase,
            BuscarPessoaPorIdUseCase buscarPessoaPorIdUseCase,
            DeleteServidorEfetivoUseCase deleteServidorEfetivoUseCase
    ) {
        this.createServidorEfetivoUseCase = createServidorEfetivoUseCase;
        this.uploadFotoUseCase = uploadFotoUseCase;
        this.updateServidorEfetivoUseCase = updateServidorEfetivoUseCase;
        this.buscarPessoaPorIdUseCase = buscarPessoaPorIdUseCase;
        this.deleteServidorEfetivoUseCase = deleteServidorEfetivoUseCase;
    }

    @Override
    public ResponseEntity<?> createPessoa(CreateServidorEfetivoRequest input) {

        final var res = createServidorEfetivoUseCase.execute(input, CreateServidorEfetivoResponse::new);
        return ResponseEntity.created(URI.create("/servidor-efetivos/"+res.id()))
                .body(res);
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
    public ResponseEntity<UpdateServidorEfetivoResponse> updateById(Long id, UpdateServidorEfetivoRequest req) {
        if (!Objects.equals(req.pesId(), id)) {
            throw DomainException.with("Serviço Efetivo identifier doesn't matches");
        }
        return  ResponseEntity.ok().body(updateServidorEfetivoUseCase.execute(req,UpdateServidorEfetivoResponse::new));
    }

    @Override
    public void deleteById(Long id) {
        deleteServidorEfetivoUseCase.execute(id);
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
