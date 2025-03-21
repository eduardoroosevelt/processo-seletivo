package br.com.eduardosilva.application.pessoa.servidorTemporario;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.pessoa.PessoaId;

import java.time.LocalDate;
import java.util.Set;

public abstract class CreateServidorTemporarioUseCase extends UseCase<CreateServidorTemporarioUseCase.Input,CreateServidorTemporarioUseCase.Output> {
    public interface Input{
        String pesNome();
        LocalDate pesDataNascimento();
        String pesSexo();
        String pesMae();
        String pesPai();
        Set<Long> enderecos();
        LocalDate stDataDemissao();
        LocalDate stDataAdmissao();
    }

    public interface Output{
        PessoaId pesId();
    }
}
