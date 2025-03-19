package br.com.eduardosilva.application.endereco;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.endereco.EnderecoID;

public abstract class CreateEnderecoUseCase extends UseCase<CreateEnderecoUseCase.Input,CreateEnderecoUseCase.Output> {

    public interface Input{
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
