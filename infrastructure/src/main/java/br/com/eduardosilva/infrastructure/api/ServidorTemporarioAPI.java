package br.com.eduardosilva.infrastructure.api;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoPreview;
import br.com.eduardosilva.domain.pessoa.ServidorTemporarioPreview;
import br.com.eduardosilva.infrastructure.pessoa.models.BuscarPessoaPorIdResponse;
import br.com.eduardosilva.infrastructure.pessoa.models.CreateServidorTemporarioRequest;
import br.com.eduardosilva.infrastructure.pessoa.models.UpdateServidorTemporarioRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = "servidor-temporario")
@Tag(name = "Servidor Temporario")
public interface ServidorTemporarioAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Criar novo servidor temporario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createServidorTemporario(@RequestBody CreateServidorTemporarioRequest input);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a Servidor efetivo by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor efetivo updated successfully"),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> updateByTemporarioId(@PathVariable(name = "id") Long id, @RequestBody UpdateServidorTemporarioRequest input);



    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Buscar uma Pessoa por id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Pessoa was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    BuscarPessoaPorIdResponse getById(@PathVariable(name = "id") Long id);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar um Servidor Temporário pelo identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servidor Temporário deletado"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void deleteById(@PathVariable(name = "id") Long id);



    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "upload"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> upload(
            @RequestParam(name = "pesId", required = false) Long pesId,
            @RequestParam(name = "fotos", required = false) List<MultipartFile> fotos
    );

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "buscar todos servidor temporário paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "servidor temporário listed"),
            @ApiResponse(responseCode = "422", description = "A query param was invalid"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Pagination<ServidorTemporarioPreview> list(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") int perPage,
            @RequestParam(name = "stDataAdmissao", required = false, defaultValue = "") LocalDate stDataAdmissao,
            @RequestParam(name = "stDataDemissao", required = false, defaultValue = "") LocalDate stDataDemissao,
            @RequestParam(name = "nome", required = false, defaultValue = "") String nome
    );
}