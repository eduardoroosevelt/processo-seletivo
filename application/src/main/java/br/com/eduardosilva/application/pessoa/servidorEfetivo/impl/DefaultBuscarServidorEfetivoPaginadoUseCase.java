package br.com.eduardosilva.application.pessoa.servidorEfetivo.impl;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.BuscarServidorEfetivoPaginadoUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.BuscarServidorTemporarioPaginadoUseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.*;

public class DefaultBuscarServidorEfetivoPaginadoUseCase extends BuscarServidorEfetivoPaginadoUseCase {

    private final PessoaGateway pessoaGateway;

    public DefaultBuscarServidorEfetivoPaginadoUseCase(PessoaGateway pessoaGateway) {
        this.pessoaGateway = pessoaGateway;
    }


    @Override
    public Pagination<ServidorEfetivoPreview> execute(ServidorEfetivoSearchQuery servidorEfetivoSearchQuery) {
        return pessoaGateway.findAllServidorEfetivo(servidorEfetivoSearchQuery);
    }
}
