package br.com.eduardosilva.application.unidade;

import br.com.eduardosilva.application.CreateEnderecoCommand;
import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.unidade.UnidadeId;

import java.util.List;
import java.util.Set;

public abstract class CreateUnidadeUseCase extends UseCase<CreateUnidadeUseCase.Input,CreateUnidadeUseCase.Output> {

    public interface Input{
         String nome();
         String sigla();
        List<CreateEnderecoCommand> enderecos();
    }

    public interface Output{
        UnidadeId unidadeId();
    }
}
