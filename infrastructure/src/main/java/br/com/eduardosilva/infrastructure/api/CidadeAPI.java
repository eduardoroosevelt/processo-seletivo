package br.com.eduardosilva.infrastructure.api;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadePreview;
import br.com.eduardosilva.infrastructure.cidade.models.CidadeResponse;
import br.com.eduardosilva.infrastructure.cidade.models.CreateCidadeRequest;
import br.com.eduardosilva.infrastructure.cidade.models.UpdateCidadeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping(value = "cidades")
@Tag(name = "Cidade")
public interface CidadeAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Criar nova cidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> create(@RequestBody CreateCidadeRequest input);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a cidade by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidade updated successfully"),
            @ApiResponse(responseCode = "404", description = "Cidade was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> updateById(@PathVariable(name = "id") Long id, @RequestBody UpdateCidadeRequest input);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all cidade paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidade listed"),
            @ApiResponse(responseCode = "422", description = "A query param was invalid"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Pagination<CidadePreview> list(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") int perPage,
            @RequestParam(name = "uf", required = false, defaultValue = "") String uf,
            @RequestParam(name = "nome", required = false, defaultValue = "") String nome
    );

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a cidade by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidade retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Cidade was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    CidadeResponse getById(@PathVariable(name = "id") Long id);


    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um cidade pelo identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cidade deletado"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void deleteById(@PathVariable(name = "id") Long id);
}
