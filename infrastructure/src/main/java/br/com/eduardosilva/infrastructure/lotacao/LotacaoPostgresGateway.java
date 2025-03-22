package br.com.eduardosilva.infrastructure.lotacao;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.lotacao.*;
import br.com.eduardosilva.infrastructure.mapper.LotacaoMapper;
import br.com.eduardosilva.infrastructure.lotacao.persistence.LotacaoJpaEntity;
import br.com.eduardosilva.infrastructure.lotacao.persistence.LotacaoRepository;
import br.com.eduardosilva.infrastructure.util.SqlUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LotacaoPostgresGateway implements LotacaoGateway {

    private final LotacaoRepository lotacaoRepository;

    public LotacaoPostgresGateway(LotacaoRepository lotacaoRepository) {
        this.lotacaoRepository = lotacaoRepository;
    }

    @Override
    public LotacaoId nextId() {
        return LotacaoId.empty();
    }

    @Override
    public Lotacao save(Lotacao lotacao) {
        LotacaoJpaEntity lotacaoJpaEntity= LotacaoMapper.INSTANCE.lotacaoToLotacaoJpaEntity(lotacao);
        lotacaoJpaEntity = this.lotacaoRepository.save(lotacaoJpaEntity);

        return LotacaoMapper.INSTANCE.lotacaJpaEntityToLotacao(lotacaoJpaEntity);
    }

    @Override
    public Optional<Lotacao> lotacaoOfId(LotacaoId anId) {
        final var t = this.lotacaoRepository.findById(anId.value());
        return this.lotacaoRepository.findById(anId.value())
                .map(LotacaoMapper.INSTANCE::lotacaJpaEntityToLotacao);
    }

    @Override
    public Pagination<LotacaoPreview> findAll(LotacaoSearchQuery search) {
        final var page = PageRequest.of(
                search.page(),
                search.perPage()
        );

        final Page<LotacaoPreview> actualPage = this.lotacaoRepository.findAll(
                SqlUtils.upper(search.lotPortaria()),
                search.unidadeId().value(),
                page);

        return  new Pagination<>(
                actualPage.getNumber(),
                actualPage.getSize(),
                actualPage.getTotalElements(),
                actualPage.toList()
        );
    }
}
