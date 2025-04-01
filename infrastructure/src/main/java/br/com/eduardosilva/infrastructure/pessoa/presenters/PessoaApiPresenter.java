package br.com.eduardosilva.infrastructure.pessoa.presenters;

import br.com.eduardosilva.application.endereco.BuscarEnderecoPorIdUseCase;
import br.com.eduardosilva.application.pessoa.BuscarPessoaPorIdUseCase;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.infrastructure.cidade.models.CidadeResponse;
import br.com.eduardosilva.infrastructure.endereco.models.EnderecoResponse;
import br.com.eduardosilva.infrastructure.pessoa.models.BuscarPessoaPorIdResponse;

import java.util.stream.Collectors;

public interface PessoaApiPresenter {
    static BuscarPessoaPorIdResponse present(final BuscarPessoaPorIdUseCase.Output out){
        return  new BuscarPessoaPorIdResponse(
                out.pesId().value(),
                out.pesNome(),
                out.pesDataNascimento(),
                out.pesSexo(),
                out.pesMae(),
                out.pesPai(),
                out.servidorTemp(),
                out.servidorEfetivo(),
                out.enderecos().stream().map(e -> new EnderecoResponse(
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
                        )).toList(),
                out.fotos()
        );
    } 
}
