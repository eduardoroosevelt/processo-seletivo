package br.com.eduardosilva.domain.unidade;

import br.com.eduardosilva.domain.Pagination;

import java.util.Optional;

public interface UnidadeGateway {
    UnidadeId nextId();
    Unidade save(Unidade unidade);
    Optional<Unidade> unidadeOfId(UnidadeId anId);
    Pagination<UnidadePreview> findAll(UnidadeSearchQuery search);

    void delete(UnidadeId unidadeId);

    Optional<Unidade> existeUnidade(String nome, String sigla);
}
