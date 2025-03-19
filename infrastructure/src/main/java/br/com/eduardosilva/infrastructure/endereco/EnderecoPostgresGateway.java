package br.com.eduardosilva.infrastructure.endereco;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.endereco.*;
import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoRepository;
import br.com.eduardosilva.infrastructure.mapper.EnderecoMapper;
import br.com.eduardosilva.infrastructure.util.SqlUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EnderecoPostgresGateway implements EnderecoGateway {

    private final EnderecoRepository enderecoRepository;

    public EnderecoPostgresGateway(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public EnderecoID nextId() {
        return EnderecoID.empty();
    }

    @Override
    public Endereco save(Endereco endereco) {

        EnderecoJpaEntity enderecoJpaEntity= EnderecoMapper.INSTANCE.enderecoToEnderecoJpaEntity(endereco);

        enderecoJpaEntity = this.enderecoRepository.save(enderecoJpaEntity);

        return EnderecoMapper.INSTANCE.enderecoJpaEntityToendereco(enderecoJpaEntity);
    }

    @Override
    public Optional<Endereco> enderecoOfId(EnderecoID anId) {
        return enderecoRepository.findById(anId.value())
                .map((op)->EnderecoMapper.INSTANCE.enderecoJpaEntityToendereco(op));
    }

    @Override
    public Pagination<EnderecoPreview> findAll(EnderecoSearchQuery search) {
        final var page = PageRequest.of(
                search.page(),
                search.perPage()
        );

        final var actualPage = this.enderecoRepository.findAll(
                SqlUtils.like(SqlUtils.upper(search.endBairro())),
                SqlUtils.like(SqlUtils.upper(search.endLogradouro())),
                SqlUtils.like(SqlUtils.upper(search.endTipoLogradouro())),
                search.endNumero(),
                search.cidadeId().value(),
                page);

        return  new Pagination<>(
                actualPage.getNumber(),
                actualPage.getSize(),
                actualPage.getTotalElements(),
                actualPage.toList()
        );
    }
}
