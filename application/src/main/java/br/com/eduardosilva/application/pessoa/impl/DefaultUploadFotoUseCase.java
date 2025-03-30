package br.com.eduardosilva.application.pessoa.impl;

import br.com.eduardosilva.application.pessoa.UploadFotoUseCase;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.exceptions.NotFoundException;
import br.com.eduardosilva.domain.pessoa.MediaResourceGateway;
import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import br.com.eduardosilva.domain.pessoa.PessoaId;

import java.util.ArrayList;
import java.util.List;

public class DefaultUploadFotoUseCase extends UploadFotoUseCase {
    private final PessoaGateway pessoaGateway;
    private final MediaResourceGateway mediaResourceGateway;

    public DefaultUploadFotoUseCase(PessoaGateway pessoaGateway, MediaResourceGateway mediaResourceGateway) {
        this.pessoaGateway = pessoaGateway;
        this.mediaResourceGateway = mediaResourceGateway;
    }

    @Override
    public Output execute(Input input) {
        final Pessoa aPessoa = this.pessoaGateway.pessoaOfId(new PessoaId(input.pesId()))
                .orElseThrow(() -> NotFoundException.with("Pessoa com id %s não pode ser encontrado".formatted(input.pesId())));

        if(input.fotos() == null){
            throw DomainException.with("Fotos não pode ser null");
        }
        List<String> links = new ArrayList<>();
        input.fotos().forEach(f ->{
            final var  foto = mediaResourceGateway.storeImage(aPessoa.id(),f);
            links.add(mediaResourceGateway.generateTemporaryLink(foto.getFpBucket()));
            aPessoa.append(foto);
        });

        pessoaGateway.save(aPessoa);
        return new StdOutput(links);
    }

    record  StdOutput(List<String> links) implements UploadFotoUseCase.Output{

    }
}
