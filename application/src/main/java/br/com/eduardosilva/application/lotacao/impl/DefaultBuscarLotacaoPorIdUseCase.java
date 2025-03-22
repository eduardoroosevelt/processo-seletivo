package br.com.eduardosilva.application.lotacao.impl;

import br.com.eduardosilva.application.endereco.impl.DefaultiBuscarEnderecoPorIdUseCase;
import br.com.eduardosilva.application.lotacao.BuscarLotacaoPorIdUseCase;
import br.com.eduardosilva.domain.exceptions.NotFoundException;
import br.com.eduardosilva.domain.lotacao.Lotacao;
import br.com.eduardosilva.domain.lotacao.LotacaoGateway;
import br.com.eduardosilva.domain.lotacao.LotacaoId;

import java.time.LocalDate;

public class DefaultBuscarLotacaoPorIdUseCase extends BuscarLotacaoPorIdUseCase {
    
    private final LotacaoGateway lotacaoGateway;

    public DefaultBuscarLotacaoPorIdUseCase(LotacaoGateway lotacaoGateway) {
        this.lotacaoGateway = lotacaoGateway;
    }

    @Override
    public Output execute(Input input) {
       return this.lotacaoGateway
               .lotacaoOfId(new LotacaoId(input.lotId()))
               .map(StdOutput::new)
                .orElseThrow(() -> NotFoundException.with("Locatação com id %s não pode ser encontrado".formatted(input.lotId())));

    }
    record StdOutput(
            Long lotId,
            String nomePessoa,
            String nomeUnidade,
            String siglaUnidade,
            LocalDate lotDataLotacao,
            LocalDate lotDataRemocao,
            String lotPortaria
    ) implements BuscarLotacaoPorIdUseCase.Output {

        public StdOutput(Lotacao out){
            this(
                    out.id().value(),
                    out.getPessoa().getPesNome(),
                    out.getUnidade().getNome(),
                    out.getUnidade().getSigla(),
                    out.getLotDataLotacao(),
                    out.getLotDataRemocao(),
                    out.getLotPortaria()
            );
        }
    }
}
