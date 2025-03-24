package br.com.eduardosilva.infrastructure.api;


import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.endereco.EnderecoPreview;
import br.com.eduardosilva.infrastructure.endereco.models.CreateEnderecoRequest;
import br.com.eduardosilva.infrastructure.endereco.models.EnderecoResponse;
import br.com.eduardosilva.infrastructure.endereco.models.UpdateEnderecoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "enderecos")
@Tag(name = "Endereço")
public interface EnderecoAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Criar nova endereco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> create(@RequestBody CreateEnderecoRequest input);

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
    ResponseEntity<?> updateById(@PathVariable(name = "id") Long id, @RequestBody UpdateEnderecoRequest input);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a endereço by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Endereço was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    EnderecoResponse getById(@PathVariable(name = "id") Long id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all Endereço paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço listed"),
            @ApiResponse(responseCode = "422", description = "A query param was invalid"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Pagination<EnderecoPreview> list(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") int perPage,
            @RequestParam(name = "endTipoLogradouro", required = false, defaultValue = "")  String endTipoLogradouro,
            @RequestParam(name = "endLogradouro", required = false, defaultValue = "")  String endLogradouro,
            @RequestParam(name = "endNumero", required = false)  Integer endNumero,
            @RequestParam(name = "endBairro", required = false, defaultValue = "")  String endBairro,
            @RequestParam(name = "cidadeId", required = false )  Long cidadeId
    );

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um endereço pelo identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço deletado"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void deleteById(@PathVariable(name = "id") Long id);
}
