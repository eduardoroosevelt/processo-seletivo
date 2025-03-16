package br.com.eduardosilva.application.cidade;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.cidade.CidadeId;

public abstract class CreateCidadeUseCase extends UseCase<CreateCidadeUseCase.Input, CreateCidadeUseCase.Output> {

    public interface Input{
        String nome();
        String uf();
    }
    public interface Output{
        CidadeId cidadeId();
    }
}
