package br.com.eduardosilva.application.pessoa.servidorEfetivo;

import br.com.eduardosilva.application.UpdateEnderecoCommand;
import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.pessoa.PessoaId;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public abstract class UpdateServidorEfetivoUseCase extends UseCase<UpdateServidorEfetivoUseCase.Input,UpdateServidorEfetivoUseCase.Output> {
    public interface Input{
        Long pesId();
        String pesNome();
        LocalDate pesDataNascimento();
        String pesSexo();
        String pesMae();
        String pesPai();
        String matricula();
        List<UpdateEnderecoCommand> enderecos();
    }

    public interface Output{
        PessoaId pesId();
    }


}
