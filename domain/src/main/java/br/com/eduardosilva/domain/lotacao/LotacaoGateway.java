package br.com.eduardosilva.domain.lotacao;

import br.com.eduardosilva.domain.Pagination;
import java.util.Optional;

public interface LotacaoGateway {
    LotacaoId nextId();
    Lotacao save(Lotacao pessoa);
    Optional<Lotacao> lotacaoOfId(final LotacaoId anId);
    Pagination<LotacaoPreview> findAll(LotacaoSearchQuery search);

    void delete(LotacaoId lotacaoId);
}
