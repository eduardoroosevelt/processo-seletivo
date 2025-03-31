package br.com.eduardosilva.application.pessoa.servidorTemporario;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.application.UpdateEnderecoCommand;
import br.com.eduardosilva.domain.pessoa.PessoaId;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public abstract class UpdateServidorTemporarioUseCase extends UseCase<UpdateServidorTemporarioUseCase.Input,UpdateServidorTemporarioUseCase.Output> {

    public interface Input{
        Long pesId();
        String pesNome();
        LocalDate pesDataNascimento();
        String pesSexo();
        String pesMae();
        String pesPai();
        LocalDate stDataDemissao();
        LocalDate stDataAdmissao();
        List<UpdateEnderecoCommand> enderecos();
    }

    public interface Output{
        PessoaId pesId();
    }
}
