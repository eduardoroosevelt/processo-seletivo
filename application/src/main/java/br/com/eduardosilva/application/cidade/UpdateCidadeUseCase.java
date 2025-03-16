package br.com.eduardosilva.application.cidade;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.cidade.CidadeId;

public abstract class UpdateCidadeUseCase extends UseCase<UpdateCidadeUseCase.Input, UpdateCidadeUseCase.Output> {

    public interface Input{
        Long cidadeId();
        String nome();
        String uf();
    }

    public interface Output{
        CidadeId cidadeId();
    }
}
