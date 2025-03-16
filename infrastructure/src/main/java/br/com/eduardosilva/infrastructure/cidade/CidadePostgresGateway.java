package br.com.eduardosilva.infrastructure.cidade;

import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadeGateway;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.infrastructure.cidade.persistence.CidadeJpaEntity;
import br.com.eduardosilva.infrastructure.cidade.persistence.CidadeRepository;
import br.com.eduardosilva.infrastructure.mapper.CidadeMapper;
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
        CidadeJpaEntity cidadeJpaEntity = CidadeMapper.INSTANCE.cidadeToCidadeJpaEntity(cidade);
        cidadeJpaEntity = cidadeRepository.save(cidadeJpaEntity);
        return CidadeMapper.INSTANCE.cidadeJpaEntityToCidade(cidadeJpaEntity);
    }

    @Override
    public Optional<Cidade> cidadeOfId(CidadeId anId) {
        return Optional.empty();
    }
}
