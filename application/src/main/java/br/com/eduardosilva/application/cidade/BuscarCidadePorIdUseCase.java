package br.com.eduardosilva.application.cidade;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.cidade.CidadeId;

public abstract class BuscarCidadePorIdUseCase extends UseCase<BuscarCidadePorIdUseCase.Input,BuscarCidadePorIdUseCase.Output> {

    public interface Input{
        Long cidadeId();
    }

    public interface  Output{
        CidadeId cidadeId();
        String nome();
        String uf();
    }
}
