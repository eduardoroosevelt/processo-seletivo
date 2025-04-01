package br.com.eduardosilva.application.pessoa.impl;

import br.com.eduardosilva.application.endereco.BuscarEnderecoPorIdUseCase;
import br.com.eduardosilva.application.endereco.impl.DefaultiBuscarEnderecoPorIdUseCase;
import br.com.eduardosilva.application.pessoa.BuscarPessoaPorIdUseCase;
import br.com.eduardosilva.domain.Entity;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.NotFoundException;
import br.com.eduardosilva.domain.pessoa.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultBuscarPessoaPorIdUseCase extends BuscarPessoaPorIdUseCase {

    private final PessoaGateway pessoaGateway;
    private final MediaResourceGateway mediaResourceGateway;

    public DefaultBuscarPessoaPorIdUseCase(PessoaGateway pessoaGateway, MediaResourceGateway mediaResourceGateway) {
        this.pessoaGateway = pessoaGateway;
        this.mediaResourceGateway = mediaResourceGateway;
    }

    @Override
    public Output execute(Input input) {
        final var pesId = new PessoaId((input.pesId()));
        return this.pessoaGateway
                .pessoaOfId(pesId)
                .map(p -> new DefaultBuscarPessoaPorIdUseCase.StdOutput(p, mediaResourceGateway) )
                .orElseThrow(()-> NotFoundException.with("Pessoa não encontrado"));
    }

    record StdOutput(
            PessoaId pesId,
            String pesNome,
            LocalDate pesDataNascimento,
            String pesSexo,
            String pesMae,
            String pesPai,
            ServidorTemporario servidorTemp,
            ServidorEfetivo servidorEfetivo,
            List<Endereco> enderecos,
            Set<String> fotos
    ) implements BuscarPessoaPorIdUseCase.Output{

        public StdOutput(Pessoa pessoa, MediaResourceGateway mediaResourceGateway){
            this(
                  pessoa.id(),
                  pessoa.getPesNome(),
                  pessoa.getPesDataNascimento(),
                  pessoa.getPesSexo(),
                  pessoa.getPesMae(),
                  pessoa.getPesPai(),
                  pessoa.getServidorTemporario(),
                  pessoa.getServidorEfetivo(),
                  pessoa.getEnderecos(),
                  pessoa.getFotos()
                          .stream()
                          .map(f->mediaResourceGateway.generateTemporaryLink(f.getFpBucket()))
                          .collect(Collectors.toSet())
            );
        }
    }
}
