package br.com.eduardosilva.application.endereco;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.endereco.EnderecoID;

public abstract class UpdateEnderecoUseCase extends UseCase<UpdateEnderecoUseCase.Input, UpdateEnderecoUseCase.Output> {
    public interface Input{
        Long enderecoId();
        String endTipoLogradouro();
        String endLogradouro();
        Integer endNumero();
        String endBairro();
        Long cidadeId();
    }

    public interface Output{
        EnderecoID enderecoId();
    }
}
