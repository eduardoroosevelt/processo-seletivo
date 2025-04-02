package br.com.eduardosilva.application.pessoa.servidorTemporario.impl;

import br.com.eduardosilva.application.pessoa.servidorTemporario.BuscarServidorTemporarioPaginadoUseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import br.com.eduardosilva.domain.pessoa.ServidorTemporarioPreview;
import br.com.eduardosilva.domain.pessoa.ServidorTemporarioSearchQuery;

public class DefaultBuscarServidorTemporarioPaginadoUseCase extends BuscarServidorTemporarioPaginadoUseCase {

    private final PessoaGateway pessoaGateway;

    public DefaultBuscarServidorTemporarioPaginadoUseCase(PessoaGateway pessoaGateway) {
        this.pessoaGateway = pessoaGateway;
    }

    @Override
    public Pagination<ServidorTemporarioPreview> execute(ServidorTemporarioSearchQuery servidorTemporarioSearchQuery) {
        return pessoaGateway.findAllServidorTemporario(servidorTemporarioSearchQuery);
    }
}
