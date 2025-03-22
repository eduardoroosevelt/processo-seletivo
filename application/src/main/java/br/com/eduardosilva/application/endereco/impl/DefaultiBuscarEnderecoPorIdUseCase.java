package br.com.eduardosilva.application.endereco.impl;

import br.com.eduardosilva.application.endereco.BuscarEnderecoPorIdUseCase;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.exceptions.NotFoundException;
import br.com.eduardosilva.domain.pessoa.MediaResourceGateway;

public class DefaultiBuscarEnderecoPorIdUseCase extends BuscarEnderecoPorIdUseCase {

    private final EnderecoGateway enderecoGateway;


    public DefaultiBuscarEnderecoPorIdUseCase(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public Output execute(Input input) {

        final var enderecoId = new EnderecoID((input.enderecoId()));
        return this.enderecoGateway
                .enderecoOfId(enderecoId)
                .map(StdOutput::new)
                .orElseThrow(()-> NotFoundException.with("Endereço não encontrado"));
       
    }

    record StdOutput(
        EnderecoID enderecoId,
        String endTipoLogradouro,
        String endLogradouro,
        Integer endNumero,
        String endBairro,
        Cidade cidade
    ) implements BuscarEnderecoPorIdUseCase.Output{

        public StdOutput(Endereco end){
            this(
                    end.id(),
                    end.getEndTipoLogradouro(),
                    end.getEndLogradouro(),
                    end.getEndNumero(),
                    end.getEndBairro(),
                    end.getCidade()
            );
        }
    }
}
