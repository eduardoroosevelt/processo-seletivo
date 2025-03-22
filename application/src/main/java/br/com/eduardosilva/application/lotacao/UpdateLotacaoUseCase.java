package br.com.eduardosilva.application.lotacao;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.lotacao.LotacaoId;

import java.time.LocalDate;

public abstract class UpdateLotacaoUseCase extends UseCase<UpdateLotacaoUseCase.Input,UpdateLotacaoUseCase.Output> {

    public interface Input{
        Long lotId();
        Long pesId();
        Long unidId();
        LocalDate lotDataLotacao();
        LocalDate lotDataRemocao();
        String lotPortaria();
    }
    public interface Output{
        LotacaoId lotId();
    }
}
