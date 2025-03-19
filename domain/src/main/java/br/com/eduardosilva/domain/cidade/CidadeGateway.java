package br.com.eduardosilva.domain.cidade;

import br.com.eduardosilva.domain.Pagination;

import java.util.Optional;

public interface CidadeGateway {
    CidadeId nextId();
    Cidade save(Cidade cidade);
    Optional<Cidade> cidadeOfId(final CidadeId anId);
    Pagination<CidadePreview> findAll(CidadeSearchQuery search);

}
