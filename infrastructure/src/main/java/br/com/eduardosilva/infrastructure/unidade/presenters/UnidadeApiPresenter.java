package br.com.eduardosilva.infrastructure.unidade.presenters;


import br.com.eduardosilva.application.unidade.BuscarUnidadePorIdUseCase;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.infrastructure.cidade.models.CidadeResponse;
import br.com.eduardosilva.infrastructure.endereco.models.EnderecoResponse;
import br.com.eduardosilva.infrastructure.unidade.models.UnidadeResponse;

import java.util.stream.Collectors;

public interface UnidadeApiPresenter {

    static UnidadeResponse present(final BuscarUnidadePorIdUseCase.Output out){

        return new UnidadeResponse(
                out.unidadeId().value(),
                out.nome(),
                out.sigla(),
                out.endereco().stream().map(e -> new EnderecoResponse(
                        e.id().value(),
                        e.getEndTipoLogradouro(),
                        e.getEndLogradouro(),
                        e.getEndNumero(),
                        e.getEndBairro(),
                        new CidadeResponse(
                                e.getCidade().id().value(),
                                e.getCidade().getNome(),
                                e.getCidade().getUf()
                        )
                        )).collect(Collectors.toList())
        );
    }
}
