package br.com.eduardosilva.application.endereco.impl;

import br.com.eduardosilva.application.endereco.BuscarEnderecoPaginadoUseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoPreview;
import br.com.eduardosilva.domain.endereco.EnderecoSearchQuery;

public class DefaultBuscarEnderecoPaginadoUseCase extends BuscarEnderecoPaginadoUseCase {

    private final EnderecoGateway enderecoGateway;

    public DefaultBuscarEnderecoPaginadoUseCase(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public Pagination<EnderecoPreview> execute(EnderecoSearchQuery enderecoSearchQuery) {
        return this.enderecoGateway.findAll(enderecoSearchQuery);
    }
}
