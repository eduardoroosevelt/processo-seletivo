package br.com.eduardosilva.infrastructure.usecases;

import br.com.eduardosilva.application.servidorEfetivo.CreateServidorEfetivoUseCase;
import br.com.eduardosilva.application.servidorEfetivo.impl.DefaultCreateServidorEfetivoUseCase;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaUseCaseConfig {
    private final PessoaGateway pessoaGateway;

    public PessoaUseCaseConfig(PessoaGateway pessoaGateway) {
        this.pessoaGateway = pessoaGateway;
    }

    @Bean
    public CreateServidorEfetivoUseCase servidorEfetivoUseCase(){
        return new DefaultCreateServidorEfetivoUseCase(pessoaGateway);
    }
}
