package br.com.eduardosilva.application.pessoa.servidorTemporario;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.pessoa.PessoaId;

import java.time.LocalDate;
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
        Set<Long> enderecos();
    }

    public interface Output{
        PessoaId pesId();
    }
}
