package br.com.eduardosilva.application.unidade.impl;

import br.com.eduardosilva.application.unidade.CreateUnidadeUseCase;
import br.com.eduardosilva.application.unidade.UpdateUnidadeUseCase;
import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.exceptions.NotFoundException;
import br.com.eduardosilva.domain.unidade.Unidade;
import br.com.eduardosilva.domain.unidade.UnidadeGateway;
import br.com.eduardosilva.domain.unidade.UnidadeId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultUpdateUnidadeUseCase extends UpdateUnidadeUseCase {

    private final UnidadeGateway unidadeGateway;
    private final CidadeGateway cidadeGateway;

    public DefaultUpdateUnidadeUseCase(
            UnidadeGateway unidadeGateway, CidadeGateway cidadeGateway) {

        this.unidadeGateway = unidadeGateway;
        this.cidadeGateway = cidadeGateway;
    }

    @Override
    public Output execute(Input input) {

        final Optional<Unidade> opUnidade=unidadeGateway.existeUnidade(
                input.nome(),
                input.sigla()
        );

        if(opUnidade.isPresent()){
            if (!opUnidade.get().id().equals(new UnidadeId(input.unidadeId()))){
                throw  DomainException.with("Já existe uma unidade com o mesmo nome ou sigla");
            }
        }


        final var aUnidade = this.unidadeGateway.unidadeOfId(new UnidadeId(input.unidadeId()))
                .orElseThrow(() -> NotFoundException.with("Unidade com id %s não pode ser encontrado".formatted(input.unidadeId())));


        List<Endereco> enderecos= input.enderecos().stream().map(er -> {
            Cidade cidade = cidadeGateway.cidadeOfId(new CidadeId(er.cidadeId()))
                    .orElseThrow(() -> DomainException.with("Cidade com id %s não pode ser encontrado".formatted(er.cidadeId())));
            EnderecoID endId = er.endId() != null ? new EnderecoID(er.endId()) : EnderecoID.empty();

            return new Endereco(
                    endId,
                    er.endTipoLogradouro(),
                    er.endLogradouro(),
                    er.endNumero(),
                    er.endBairro(),
                    cidade);

        }).toList();

        aUnidade
                .updateNome(input.nome())
                .updateSigla(input.sigla())
                .updateEnderecos(enderecos);

        return new StdOutput(unidadeGateway.save(aUnidade));
    }

    record StdOutput(UnidadeId unidadeId) implements UpdateUnidadeUseCase.Output{
        public  StdOutput(Unidade out){
            this(out.id());
        }
    }


}
