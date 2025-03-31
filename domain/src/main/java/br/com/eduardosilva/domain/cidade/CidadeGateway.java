package br.com.eduardosilva.domain.cidade;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.endereco.EnderecoID;

import java.util.List;
import java.util.Optional;

public interface CidadeGateway {
    CidadeId nextId();
    Cidade save(Cidade cidade);
    Optional<Cidade> cidadeOfId(final CidadeId anId);
    Pagination<CidadePreview> findAll(CidadeSearchQuery search);
    List<CidadeId> existsByIds(Iterable<CidadeId> ids);
    void delete(CidadeId cidadeId);
}
