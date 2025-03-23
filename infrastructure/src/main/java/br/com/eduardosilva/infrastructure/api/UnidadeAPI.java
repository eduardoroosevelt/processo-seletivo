package br.com.eduardosilva.infrastructure.api;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.unidade.UnidadePreview;
import br.com.eduardosilva.infrastructure.unidade.models.CreateUnidadeRequest;
import br.com.eduardosilva.infrastructure.unidade.models.UnidadeResponse;
import br.com.eduardosilva.infrastructure.unidade.models.UpdateUnidadeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "unidades")
@Tag(name = "Unidade")
public interface UnidadeAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Criar nova unidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> create(@RequestBody CreateUnidadeRequest input);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a endereço by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço updated successfully"),
            @ApiResponse(responseCode = "404", description = "Endereço was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> updateById(@PathVariable(name = "id") Long id, @RequestBody UpdateUnidadeRequest input);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a unidade by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidade retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Unidade was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    UnidadeResponse getById(@PathVariable(name = "id") Long id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all cidade paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidade listed"),
            @ApiResponse(responseCode = "422", description = "A query param was invalid"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Pagination<UnidadePreview> list(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") int perPage,
            @RequestParam(name = "nome", required = false, defaultValue = "")  String nome,
            @RequestParam(name = "sigla", required = false, defaultValue = "")  String sigla
    );


    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um unidade pelo identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Unidade deletado"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void deleteById(@PathVariable(name = "id") Long id);
}
