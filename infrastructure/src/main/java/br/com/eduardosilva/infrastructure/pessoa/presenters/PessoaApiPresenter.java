package br.com.eduardosilva.infrastructure.pessoa.presenters;

import br.com.eduardosilva.application.endereco.BuscarEnderecoPorIdUseCase;
import br.com.eduardosilva.application.pessoa.BuscarPessoaPorIdUseCase;
import br.com.eduardosilva.domain.endereco.EnderecoID;
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
                out.enderecos().stream().map(EnderecoID::value).collect(Collectors.toSet()),
                out.fotos()
        );
    } 
}
