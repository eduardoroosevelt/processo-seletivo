package br.com.eduardosilva.application.pessoa.servidorEfetivo.impl;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.BuscarEnderecoByNomeServidorUseCase;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.EnderecoFuncionalPorNomeServidorPreview;
import br.com.eduardosilva.domain.pessoa.EnderecoFuncionalPorNomeServidorSearch;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoPorUnidadeIdPreview;

public class DefaultBuscarEnderecoByNomeServidorUseCase extends BuscarEnderecoByNomeServidorUseCase {
    private PessoaGateway pessoaGateway;

    public DefaultBuscarEnderecoByNomeServidorUseCase(PessoaGateway pessoaGateway) {
        this.pessoaGateway = pessoaGateway;
    }

    @Override
    public Pagination<EnderecoFuncionalPorNomeServidorPreview> execute(EnderecoFuncionalPorNomeServidorSearch enderecoFuncionalPorNomeServidorSearch) {
        return pessoaGateway.findEnderecoByNomeServidor(enderecoFuncionalPorNomeServidorSearch);
    }
}
