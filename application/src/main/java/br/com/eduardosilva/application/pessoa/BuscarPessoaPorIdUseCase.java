package br.com.eduardosilva.application.pessoa;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.pessoa.PessoaFoto;
import br.com.eduardosilva.domain.pessoa.PessoaId;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivo;
import br.com.eduardosilva.domain.pessoa.ServidorTemporario;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


public abstract class BuscarPessoaPorIdUseCase extends UseCase<BuscarPessoaPorIdUseCase.Input,BuscarPessoaPorIdUseCase.Output> {
    public interface Input{
        Long pesId();
    }

    public interface Output{
        PessoaId pesId();
        String pesNome();
        LocalDate pesDataNascimento();
        String pesSexo();
        String pesMae();
        String pesPai();
        ServidorTemporario servidorTemp();
        ServidorEfetivo servidorEfetivo();
        List<Endereco> enderecos();
        Set<String> fotos();
    }
}
