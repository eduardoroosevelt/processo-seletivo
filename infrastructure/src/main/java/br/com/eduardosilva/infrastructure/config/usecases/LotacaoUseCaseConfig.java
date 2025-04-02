package br.com.eduardosilva.infrastructure.config.usecases;

import br.com.eduardosilva.application.lotacao.*;
import br.com.eduardosilva.application.lotacao.impl.*;
import br.com.eduardosilva.domain.lotacao.LotacaoGateway;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import br.com.eduardosilva.domain.unidade.UnidadeGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LotacaoUseCaseConfig {
    private final PessoaGateway pessoaGateway;
    private final UnidadeGateway unidadeGateway;
    private final LotacaoGateway lotacaoGateway;

    public LotacaoUseCaseConfig(
            PessoaGateway pessoaGateway,
            UnidadeGateway unidadeGateway,
            LotacaoGateway lotacaoGateway) {

        this.pessoaGateway = pessoaGateway;
        this.unidadeGateway = unidadeGateway;
        this.lotacaoGateway = lotacaoGateway;
    }


    @Bean
    public CreateLotacaoUseCase createLotacaoUseCase(){
        return new DefaultCreateLotacaoUseCase(
                lotacaoGateway,
                pessoaGateway,
                unidadeGateway
        );
    }

    @Bean
    public UpdateLotacaoUseCase updateLotacaoUseCase(){
        return new DefaultUpdateLotacaoUseCase(
                lotacaoGateway,
                pessoaGateway,
                unidadeGateway
        );
    }

    @Bean
    public BuscarLotacaoPorIdUseCase buscarLotacaoPorIdUseCase(){
        return new DefaultBuscarLotacaoPorIdUseCase(lotacaoGateway);
    }

    @Bean
    public DeleteLotacaoUseCase deleteLotacaoUseCase(){
        return new DefaultDeleteLotacaoUseCase(lotacaoGateway);
    }

    @Bean
    public BuscarLotacaoPaginadoUseCase buscarLotacaoPaginadoUseCase(){
        return new DefaultBuscarLotacaoPaginadoUseCase(lotacaoGateway);
    }
}
