package br.com.eduardosilva.application.pessoa.servidorTemporario;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.application.CreateEnderecoCommand;
import br.com.eduardosilva.domain.pessoa.PessoaId;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public abstract class CreateServidorTemporarioUseCase extends UseCase<CreateServidorTemporarioUseCase.Input,CreateServidorTemporarioUseCase.Output> {
    public interface Input{
        String pesNome();
        LocalDate pesDataNascimento();
        String pesSexo();
        String pesMae();
        String pesPai();
        List<CreateEnderecoCommand> enderecos();
        LocalDate stDataDemissao();
        LocalDate stDataAdmissao();
    }

    public interface Output{
        PessoaId pesId();
    }


}
