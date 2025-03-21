package br.com.eduardosilva.application.endereco.impl;

import br.com.eduardosilva.application.cidade.impl.DefaultCreateCidade;
import br.com.eduardosilva.application.endereco.CreateEnderecoUseCase;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.exceptions.NotFoundException;

public class DefaultCreateEnderecoUseCase extends CreateEnderecoUseCase {

    private final EnderecoGateway enderecoGateway;
    private final CidadeGateway cidadeGateway;

    public DefaultCreateEnderecoUseCase(EnderecoGateway enderecoGateway,
                                        CidadeGateway cidadeGateway) {
        this.enderecoGateway = enderecoGateway;
        this.cidadeGateway = cidadeGateway;
    }

    @Override
    public Output execute(Input input) {
        Cidade cidade = this.cidadeGateway.cidadeOfId(new CidadeId(input.cidadeId()))
                .orElseThrow(() -> NotFoundException.with("Cidade com id %s não pode ser encontrado".formatted(input.cidadeId())));


        Endereco endereco = new Endereco(
                this.enderecoGateway.nextId(),
                input.endTipoLogradouro(),
                input.endLogradouro(),
                input.endNumero(),
                input.endBairro(),
                cidade
        );

        final var enderecoBD = enderecoGateway.save(endereco);
        return new StdOutput(enderecoBD.id());
    }

    record StdOutput(EnderecoID enderecoId) implements CreateEnderecoUseCase.Output {}
}
