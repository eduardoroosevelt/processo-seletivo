package br.com.eduardosilva.domain.cidade;

import java.util.Optional;

public interface CidadeGateway {
    CidadeId nextId();
    Cidade save(Cidade cidade);
    Optional<Cidade> cidadeOfId(final CidadeId anId);
}
