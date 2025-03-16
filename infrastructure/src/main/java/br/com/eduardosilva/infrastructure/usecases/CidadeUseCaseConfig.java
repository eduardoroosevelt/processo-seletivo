package br.com.eduardosilva.infrastructure.usecases;

import br.com.eduardosilva.application.cidade.CreateCidadeUseCase;
import br.com.eduardosilva.application.cidade.impl.DefaultCreateCidade;
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
}
