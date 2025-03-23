package br.com.eduardosilva.infrastructure.config.usecases;

import br.com.eduardosilva.application.cidade.*;
import br.com.eduardosilva.application.cidade.impl.*;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CidadeUseCaseConfig {

    private final CidadeGateway cidadeGateway;

    public CidadeUseCaseConfig(CidadeGateway cidadeGateway) {
        this.cidadeGateway = cidadeGateway;
    }

    @Bean
    public CreateCidadeUseCase createCidadeUseCase(){
        return new DefaultCreateCidade(cidadeGateway);
    }

    @Bean
    public UpdateCidadeUseCase updateCidadeUseCase(){
        return new DefaultUpdateCidade(cidadeGateway);
    }

    @Bean
    public BuscaCidadePaginadoUseCase buscaCidadePaginadoUseCase(){
        return new DefaultBuscaCidadePaginadoUseCase((cidadeGateway));
    }

    @Bean
    public BuscarCidadePorIdUseCase buscarCidadePorIdUseCase(){
        return new DefaultBuscarCidadePorIdUseCase(cidadeGateway);
    }

    @Bean
    public DeleteCidadeUseCase deleteCidadeUseCase(){
        return new DefaultDeleteCidadeUseCase(cidadeGateway);
    }
}
