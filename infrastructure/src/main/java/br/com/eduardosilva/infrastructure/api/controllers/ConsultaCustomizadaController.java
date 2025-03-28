package br.com.eduardosilva.infrastructure.api.controllers;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.BuscarEnderecoByNomeServidorUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.BuscarServidorEfetivoPorUnidadeId;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.EnderecoFuncionalPorNomeServidorPreview;
import br.com.eduardosilva.domain.pessoa.EnderecoFuncionalPorNomeServidorSearch;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoPorUnidadeIdSearchQuery;
import br.com.eduardosilva.domain.unidade.UnidadeId;
import br.com.eduardosilva.infrastructure.api.ConsultasCustomizadasAPI;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultaCustomizadaController implements ConsultasCustomizadasAPI {
    private final BuscarServidorEfetivoPorUnidadeId buscarServidorEfetivoPorUnidadeId;
    private final BuscarEnderecoByNomeServidorUseCase buscarEnderecoByNomeServidorUseCase;

    public ConsultaCustomizadaController(BuscarServidorEfetivoPorUnidadeId buscarServidorEfetivoPorUnidadeId, BuscarEnderecoByNomeServidorUseCase buscarEnderecoByNomeServidorUseCase) {
        this.buscarServidorEfetivoPorUnidadeId = buscarServidorEfetivoPorUnidadeId;
        this.buscarEnderecoByNomeServidorUseCase = buscarEnderecoByNomeServidorUseCase;
    }

    @Override
    public Pagination<BuscarServidorEfetivoPorUnidadeId.Output> buscarServidoresLotadosEmDeterminadaUnidade(int page, int perPage, Long unidadeId) {
        final var input = new ServidorEfetivoPorUnidadeIdSearchQuery(page,perPage, new UnidadeId(unidadeId));
        return this.buscarServidorEfetivoPorUnidadeId.execute(input);
    }

    @Override
    public Pagination<EnderecoFuncionalPorNomeServidorPreview> findEnderecoByNomeServidor(int page, int perPage, String nomeParte) {
        final var input = new EnderecoFuncionalPorNomeServidorSearch(page,perPage,nomeParte);
        return buscarEnderecoByNomeServidorUseCase.execute(input);
    }
}
