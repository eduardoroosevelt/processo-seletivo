package br.com.eduardosilva.application.unidade.impl;

import br.com.eduardosilva.application.unidade.CreateUnidadeUseCase;
import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoGateway;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.unidade.Unidade;
import br.com.eduardosilva.domain.unidade.UnidadeGateway;
import br.com.eduardosilva.domain.unidade.UnidadeId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultCreateUnidadeUseCase extends CreateUnidadeUseCase {

    private final UnidadeGateway unidadeGateway;
    private final EnderecoGateway enderecoGateway;
    private final CidadeGateway cidadeGateway;

    public DefaultCreateUnidadeUseCase(UnidadeGateway unidadeGateway,
                                       EnderecoGateway enderecoGateway, CidadeGateway cidadeGateway) {
        this.unidadeGateway = unidadeGateway;
        this.enderecoGateway = enderecoGateway;
        this.cidadeGateway = cidadeGateway;
    }

    @Override
    public Output execute(Input input) {

        final Optional<Unidade> opUnidade=unidadeGateway.existeUnidade(
                input.nome(),
                input.sigla()
        );

        if(opUnidade.isPresent()){
            throw  DomainException.with("Já existe uma unidade com o mesmo nome ou sigla");
        }

        List<Endereco> enderecos= input.enderecos().stream().map(er -> {
            Cidade cidade = cidadeGateway.cidadeOfId(new CidadeId(er.cidadeId()))
                    .orElseThrow(() -> DomainException.with("Cidade com id %s não pode ser encontrado".formatted(er.cidadeId())));

            return new Endereco(
                    EnderecoID.empty(),
                    er.endTipoLogradouro(),
                    er.endLogradouro(),
                    er.endNumero(),
                    er.endBairro(),
                    cidade);

        }).collect(Collectors.toList());

        Unidade unidade = new Unidade(
                unidadeGateway.nextId(),
                input.nome(),
                input.sigla(),
                enderecos
        );
        return new StdOutput(unidadeGateway.save(unidade));
    }

    record StdOutput(UnidadeId unidadeId) implements CreateUnidadeUseCase.Output{
        public  StdOutput(Unidade out){
            this(out.id());
        }
    }

    private void validateEnderecos(final Set<EnderecoID> ids){
        if (ids == null || ids.isEmpty()) {
            return ;
        }

        final var retrievedIds = enderecoGateway.existsByIds(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrievedIds);

            final var missingIdsMessage = missingIds.stream()
                    .map(Identifier::value)
                    .map(id ->id.toString())
                    .collect(Collectors.joining(", "));

            throw DomainException.with("Alguns Endereços não pode ser encontrado: %s".formatted(missingIdsMessage) );
        }
    }


}
