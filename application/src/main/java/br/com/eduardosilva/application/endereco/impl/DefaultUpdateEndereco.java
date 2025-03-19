package br.com.eduardosilva.application.endereco.impl;

import br.com.eduardosilva.application.endereco.UpdateEnderecoUseCase;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;

public class DefaultUpdateEndereco extends UpdateEnderecoUseCase {
    private final EnderecoGateway enderecoGateway;
    private final CidadeGateway cidadeGateway;

    public DefaultUpdateEndereco(EnderecoGateway enderecoGateway,
                                 CidadeGateway cidadeGateway) {
        this.enderecoGateway = enderecoGateway;
        this.cidadeGateway = cidadeGateway;
    }

    @Override
    public Output execute(Input input) {

        final var aEndereco = this.enderecoGateway.enderecoOfId(new EnderecoID(input.enderecoId()))
                .orElseThrow(() -> DomainException.with("Endereco com id %s não pode ser encontrado".formatted(input.cidadeId())));

        final var aCidade = this.cidadeGateway.cidadeOfId(new CidadeId(input.cidadeId()))
                .orElseThrow(() -> DomainException.with("Cidade com id %s não pode ser encontrado".formatted(input.cidadeId())));

        aEndereco.updateEndBairro(input.endBairro());
        aEndereco.updateEndNumero(input.endNumero());
        aEndereco.updateEndLogradouro(input.endLogradouro());
        aEndereco.updateEndTipoLogradouro(input.endTipoLogradouro());
        aEndereco.updateCidade(aCidade);

        return new StdOutput(enderecoGateway.save(aEndereco));
    }

    record StdOutput(EnderecoID enderecoId) implements UpdateEnderecoUseCase.Output {

        public StdOutput(Endereco end){
             this(end.id());
        }
    }
}
