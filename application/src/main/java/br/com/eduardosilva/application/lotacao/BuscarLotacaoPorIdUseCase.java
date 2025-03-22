package br.com.eduardosilva.application.lotacao;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.unidade.Unidade;

import java.time.LocalDate;

public abstract class BuscarLotacaoPorIdUseCase extends UseCase<BuscarLotacaoPorIdUseCase.Input,BuscarLotacaoPorIdUseCase.Output> {
    public interface Input{
        Long lotId();
    }

    public interface Output{
        Long lotId();
        String nomePessoa();
        String nomeUnidade();
        String siglaUnidade();
        LocalDate lotDataLotacao();
        LocalDate lotDataRemocao();
        String lotPortaria();
    }
}
