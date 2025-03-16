package br.com.eduardosilva.infrastructure.cidade;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.cidade.*;
import br.com.eduardosilva.infrastructure.cidade.persistence.CidadeJpaEntity;
import br.com.eduardosilva.infrastructure.cidade.persistence.CidadeRepository;
import br.com.eduardosilva.infrastructure.mapper.CidadeMapper;
import br.com.eduardosilva.infrastructure.util.SqlUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CidadePostgresGateway implements CidadeGateway {

    private final CidadeRepository cidadeRepository;

    public CidadePostgresGateway(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public CidadeId nextId() {
        CidadeId a= CidadeId.empty();
        return  a;
    }

    @Override
    public Cidade save(Cidade cidade) {
        CidadeJpaEntity cidadeJpaEntity;
        cidadeJpaEntity = CidadeMapper.INSTANCE.cidadeToCidadeJpaEntity(cidade);

        cidadeJpaEntity = cidadeRepository.save(cidadeJpaEntity);
        return CidadeMapper.INSTANCE.cidadeJpaEntityToCidade(cidadeJpaEntity);
    }

    @Override
    public Optional<Cidade> cidadeOfId(CidadeId anId) {
        return cidadeRepository.findById(anId.value())
                .map((op)->CidadeMapper.INSTANCE.cidadeJpaEntityToCidade(op));

    }

    @Override
    public Pagination<CidadePreview> findAll(CidadeSearchQuery search) {
        final var page = PageRequest.of(
                search.page(),
                search.perPage()
        );

        final var actualPage = this.cidadeRepository.findAll(
                SqlUtils.like(SqlUtils.upper(search.nome())),
                SqlUtils.like(SqlUtils.upper(search.uf())),
                page);

        return new Pagination<>(
                actualPage.getNumber(),
                actualPage.getSize(),
                actualPage.getTotalElements(),
                actualPage.toList()
        );
    }
}
