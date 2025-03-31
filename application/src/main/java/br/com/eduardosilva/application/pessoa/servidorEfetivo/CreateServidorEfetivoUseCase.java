package br.com.eduardosilva.application.pessoa.servidorEfetivo;

import br.com.eduardosilva.application.CreateEnderecoCommand;
import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.pessoa.PessoaId;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public abstract class CreateServidorEfetivoUseCase extends UseCase<CreateServidorEfetivoUseCase.Input,CreateServidorEfetivoUseCase.Output> {
    public interface Input{
         String pesNome();
         LocalDate pesDataNascimento();
         String pesSexo();
         String pesMae();
         String pesPai();
         String matricula();
        List<CreateEnderecoCommand> enderecos();
    }

    public interface Output{
        PessoaId pesId();
    }


}
