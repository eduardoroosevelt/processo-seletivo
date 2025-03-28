package br.com.eduardosilva.infrastructure.api;


import br.com.eduardosilva.application.pessoa.servidorEfetivo.BuscarServidorEfetivoPorUnidadeId;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.EnderecoFuncionalPorNomeServidorPreview;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "consultas")
@Tag(name = "Consulta")
public interface ConsultasCustomizadasAPI {

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
