package br.com.eduardosilva.application.pessoa.servidorEfetivo.impl;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.BuscarServidorEfetivoPorUnidadeId;
import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.MediaResourceGateway;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoPorUnidadeIdSearchQuery;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


public class DefaultBuscarServidorEfetivoPorUnidadeId extends BuscarServidorEfetivoPorUnidadeId {

    private final MediaResourceGateway mediaResourceGateway;
    private final PessoaGateway pessoaGateway;

    public DefaultBuscarServidorEfetivoPorUnidadeId(MediaResourceGateway mediaResourceGateway, PessoaGateway pessoaGateway) {
        this.mediaResourceGateway = mediaResourceGateway;
        this.pessoaGateway = pessoaGateway;
    }


    @Override
    public Pagination<BuscarServidorEfetivoPorUnidadeId.Output> execute(ServidorEfetivoPorUnidadeIdSearchQuery servidorEfetivoPorUnidadeIdSearchQuery) {

        return pessoaGateway
                .findServidoresEfetivosByUnidade(servidorEfetivoPorUnidadeIdSearchQuery)
                .map(p -> new StdOutput(
                        p.nome(),
                        p.unidadeLotacao(),
                        p.idade(),
                        p.fotos()
                                .stream()
                                .map(mediaResourceGateway::generateTemporaryLink)
                                .collect(Collectors.toSet())
                ));
    }
    
    
    record StdOutput(
            String nome,
            String unidadeNome,
            Integer idade,
            Set<String> fotos
    ) implements BuscarServidorEfetivoPorUnidadeId.Output {


    }
}
