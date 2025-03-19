package br.com.eduardosilva.application.endereco;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.endereco.EnderecoID;

public abstract class BuscarEnderecoPorIdUseCase extends UseCase<BuscarEnderecoPorIdUseCase.Input,BuscarEnderecoPorIdUseCase.Output> {

    public interface Input{
        Long enderecoId();
    }

    public interface Output{
        EnderecoID enderecoId();
        String endTipoLogradouro();
        String endLogradouro();
        Integer endNumero();
        String endBairro();
        Cidade cidade();
    }
}
