package br.com.eduardosilva.application.cidade.impl;

import br.com.eduardosilva.application.cidade.BuscarCidadePorIdUseCase;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.exceptions.NotFoundException;

public class DefaultBuscarCidadePorIdUseCase extends BuscarCidadePorIdUseCase {

    private final CidadeGateway cidadeGateway;

    public DefaultBuscarCidadePorIdUseCase(CidadeGateway cidadeGateway) {
        this.cidadeGateway = cidadeGateway;
    }

    @Override
    public Output execute(Input input) {
        final var aCidadeId = new CidadeId(input.cidadeId());
        return this.cidadeGateway
                .cidadeOfId(aCidadeId)
                .map(StdOut::new)
                .orElseThrow(()-> NotFoundException.with("Cidade não encontrado"));
    }

    record StdOut(
        CidadeId cidadeId,
        String nome,
        String uf
    ) implements BuscarCidadePorIdUseCase.Output{
        public StdOut(Cidade aCidade){
            this(aCidade.id(),aCidade.getNome(),aCidade.getUf());
        }
    }
}
