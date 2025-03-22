package br.com.eduardosilva.infrastructure.config.usecases;

import br.com.eduardosilva.application.endereco.BuscarEnderecoPaginadoUseCase;
import br.com.eduardosilva.application.endereco.BuscarEnderecoPorIdUseCase;
import br.com.eduardosilva.application.endereco.CreateEnderecoUseCase;
import br.com.eduardosilva.application.endereco.UpdateEnderecoUseCase;
import br.com.eduardosilva.application.endereco.impl.DefaultBuscarEnderecoPaginadoUseCase;
import br.com.eduardosilva.application.endereco.impl.DefaultCreateEnderecoUseCase;
import br.com.eduardosilva.application.endereco.impl.DefaultUpdateEndereco;
import br.com.eduardosilva.application.endereco.impl.DefaultiBuscarEnderecoPorIdUseCase;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoUseCaseConfig {
    private final CidadeGateway cidadeGateway;
    private final EnderecoGateway enderecoGateway;


    public EnderecoUseCaseConfig(CidadeGateway cidadeGateway, EnderecoGateway enderecoGateway) {
        this.cidadeGateway = cidadeGateway;
        this.enderecoGateway = enderecoGateway;
    }

    @Bean
    public CreateEnderecoUseCase createEnderecoUseCase(){
        return new DefaultCreateEnderecoUseCase(enderecoGateway,cidadeGateway);
    }

    @Bean
    public UpdateEnderecoUseCase updateEnderecoUseCase(){
        return new DefaultUpdateEndereco(enderecoGateway,cidadeGateway);
    }

    @Bean
    public BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase(){
        return new DefaultiBuscarEnderecoPorIdUseCase(enderecoGateway);
    }

    @Bean
    BuscarEnderecoPaginadoUseCase buscarEnderecoPaginadoUseCase(){
        return new DefaultBuscarEnderecoPaginadoUseCase(enderecoGateway);
    }
}
