package br.com.eduardosilva.application.unidade;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.unidade.UnidadeId;

import java.util.List;
import java.util.Set;

public abstract class BuscarUnidadePorIdUseCase extends UseCase<BuscarUnidadePorIdUseCase.Input,BuscarUnidadePorIdUseCase.Output> {

    public interface Input{
        Long unidadeId();
    }

    public interface Output{
        UnidadeId unidadeId();
        String nome();
        String sigla();
        List<Endereco> endereco();
    }
}
