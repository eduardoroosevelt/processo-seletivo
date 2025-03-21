package br.com.eduardosilva.application.pessoa.servidorEfetivo;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.pessoa.PessoaId;

import java.time.LocalDate;
import java.util.Set;

public abstract class CreateServidorEfetivoUseCase extends UseCase<CreateServidorEfetivoUseCase.Input,CreateServidorEfetivoUseCase.Output> {
    public interface Input{
         String pesNome();
         LocalDate pesDataNascimento();
         String pesSexo();
         String pesMae();
         String pesPai();
         String matricula();
         Set<Long> enderecos();
    }

    public interface Output{
        PessoaId pesId();
    }
}
