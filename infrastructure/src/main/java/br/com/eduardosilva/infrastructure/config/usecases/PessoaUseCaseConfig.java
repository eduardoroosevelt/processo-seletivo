package br.com.eduardosilva.infrastructure.config.usecases;

import br.com.eduardosilva.application.pessoa.BuscarPessoaPorIdUseCase;
import br.com.eduardosilva.application.pessoa.UploadFotoUseCase;
import br.com.eduardosilva.application.pessoa.impl.DefaultBuscarPessoaPorIdUseCase;
import br.com.eduardosilva.application.pessoa.impl.DefaultUploadFotoUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.BuscarEnderecoByNomeServidorUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.BuscarServidorEfetivoPorUnidadeId;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.CreateServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.UpdateServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.impl.DefaultBuscarEnderecoByNomeServidorUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.impl.DefaultBuscarServidorEfetivoPorUnidadeId;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.impl.DefaultCreateServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.impl.DefaultUpdateServidorEfetivoUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.CreateServidorTemporarioUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.UpdateServidorTemporarioUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.impl.DefaultCreateServidorTemporarioUseCase;
import br.com.eduardosilva.application.pessoa.servidorTemporario.impl.DefaultUpdateServidorTemporarioUseCase;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.pessoa.MediaResourceGateway;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaUseCaseConfig {
    private final PessoaGateway pessoaGateway;
    private final EnderecoGateway enderecoGateway;
    private final MediaResourceGateway mediaResourceGateway;

    public PessoaUseCaseConfig(PessoaGateway pessoaGateway, EnderecoGateway enderecoGateway, MediaResourceGateway mediaResourceGateway) {
        this.pessoaGateway = pessoaGateway;
        this.enderecoGateway = enderecoGateway;
        this.mediaResourceGateway = mediaResourceGateway;
    }

    @Bean
    public CreateServidorEfetivoUseCase servidorEfetivoUseCase(){
        return new DefaultCreateServidorEfetivoUseCase(pessoaGateway,enderecoGateway);
    }

    @Bean
    public UploadFotoUseCase uploadFotoUseCase(){
        return new DefaultUploadFotoUseCase(pessoaGateway,mediaResourceGateway);
    }

    @Bean
    public UpdateServidorEfetivoUseCase updateServidorEfetivoUseCase(){
        return new DefaultUpdateServidorEfetivoUseCase(pessoaGateway,enderecoGateway);
    }

    @Bean
    public CreateServidorTemporarioUseCase createServidorTemporarioUseCase(){
        return new DefaultCreateServidorTemporarioUseCase(pessoaGateway,enderecoGateway);
    }

    @Bean
    public UpdateServidorTemporarioUseCase updateServidorTemporarioUseCase(){
        return new DefaultUpdateServidorTemporarioUseCase(pessoaGateway,enderecoGateway);
    }

    @Bean
    public BuscarPessoaPorIdUseCase buscarPessoaPorIdUseCase(){
        return new DefaultBuscarPessoaPorIdUseCase(pessoaGateway,mediaResourceGateway);
    }

    @Bean
    public BuscarServidorEfetivoPorUnidadeId buscarServidorEfetivoPorUnidadeId(){
        return new DefaultBuscarServidorEfetivoPorUnidadeId(mediaResourceGateway,pessoaGateway);
    }

    @Bean
    public BuscarEnderecoByNomeServidorUseCase buscarEnderecoByNomeServidorUseCase(){
        return  new DefaultBuscarEnderecoByNomeServidorUseCase(pessoaGateway);
    }
}
