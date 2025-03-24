package br.com.eduardosilva.infrastructure.api;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.BuscarServidorEfetivoPorUnidadeId;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.endereco.EnderecoPreview;
import br.com.eduardosilva.domain.pessoa.EnderecoFuncionalPorNomeServidorPreview;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoPorUnidadeIdPreview;
import br.com.eduardosilva.infrastructure.endereco.models.EnderecoResponse;
import br.com.eduardosilva.infrastructure.pessoa.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping(value = "servidor")
@Tag(name = "Servidor")
public interface ServidorAPI {
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "efetivo"
    )
    @Operation(summary = "Criar novo servidor efetivo sem as fotos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createPessoa(@RequestBody CreateServidorEfetivoRequest input);

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "temporario"
    )
    @Operation(summary = "Criar novo servidor temporario sem as fotos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createServidorTemporario(@RequestBody CreateServidorTemporarioRequest input);

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


    @PutMapping(
            value = "efetivo/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a Servidor efetivo by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor efetivo updated successfully"),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> updateById(@PathVariable(name = "id") Long id, @RequestBody UpdateServidorEfetivoRequest input);

    @PutMapping(
            value = "temporario/{id}",
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar os servidores efetivos lotados em determinada unidade paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidores paginado"),
            @ApiResponse(responseCode = "422", description = "A query param was invalid"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Pagination<BuscarServidorEfetivoPorUnidadeId.Output> buscarServidoresLotadosEmDeterminadaUnidade(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") int perPage,
            @RequestParam(name = "unidadeId", required = true )  Long unidadeId
    );

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/buscar-por-endereco-funcional"
    )
    @Operation(summary = "Buscar endereço funcional (da unidade onde o servidor é lotado) a partir de uma parte do nome do servidor efetivo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidade listed"),
            @ApiResponse(responseCode = "422", description = "A query param was invalid"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Pagination<EnderecoFuncionalPorNomeServidorPreview> findEnderecoByNomeServidor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") int perPage,
            @RequestParam(name = "nome", required = true )  String unidadeId
    );
}
