package br.com.eduardosilva.application.unidade;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.unidade.UnidadeId;

import java.util.Set;

public abstract class UpdateUnidadeUseCase extends UseCase<UpdateUnidadeUseCase.Input,UpdateUnidadeUseCase.Output> {

    public interface Input{
        Long unidadeId();
        String nome();
        String sigla();
        Set<Long> enderecos();
    }

    public interface Output{
        UnidadeId unidadeId();
    }
}
