package br.com.eduardosilva.infrastructure.unidade;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.unidade.*;
import br.com.eduardosilva.infrastructure.mapper.UnidadeMapper;
import br.com.eduardosilva.infrastructure.unidade.persistence.UnidadeJpaEntity;
import br.com.eduardosilva.infrastructure.unidade.persistence.UnidadeRepository;
import br.com.eduardosilva.infrastructure.util.SqlUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UnidadePostgreGateway implements UnidadeGateway {

    private final UnidadeRepository unidadeRepository;

    public UnidadePostgreGateway(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public UnidadeId nextId() {
        return UnidadeId.empty();
    }

    @Override
    @Transactional()
    public Unidade save(final Unidade unidade) {
        UnidadeJpaEntity unidadeJpaEntity = UnidadeMapper.INSTANCE.unidadeToUnidadeJpaEntity(unidade);
        unidadeJpaEntity = unidadeRepository.save(unidadeJpaEntity);

        Unidade uniretorno = UnidadeMapper.INSTANCE.unidadeJpaEntityToUnidade(unidadeJpaEntity);
        return uniretorno;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Unidade> unidadeOfId(UnidadeId anId) {
        return unidadeRepository.findById(anId.value())
                .map(UnidadeMapper.INSTANCE::unidadeJpaEntityToUnidade);

    }

    @Override
    public Pagination<UnidadePreview> findAll(UnidadeSearchQuery search) {

        final var page = PageRequest.of(
                search.page(),
                search.perPage()
        );

        final Page<UnidadePreview> actualPage = this.unidadeRepository.findAlls(
                SqlUtils.upper(search.nome()),
                SqlUtils.upper(search.sigla()),
                page);


        return new Pagination<>(
                actualPage.getNumber(),
                actualPage.getSize(),
                actualPage.getTotalElements(),
                actualPage.toList()
        );
    }

    @Override
    public void delete(UnidadeId unidadeId) {
        final var uniId = unidadeId.value();
        if (this.unidadeRepository.existsById(uniId)) {
            this.unidadeRepository.deleteById(uniId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Unidade> existeUnidade(String nome, String sigla) {
        return this.unidadeRepository.existeUnidade(
                SqlUtils.upper(nome),
                SqlUtils.upper(sigla)
        ).map(UnidadeMapper.INSTANCE::unidadeJpaEntityToUnidade);
    }
}
