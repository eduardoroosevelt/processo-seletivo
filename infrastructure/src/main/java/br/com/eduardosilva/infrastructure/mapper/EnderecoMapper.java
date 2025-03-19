package br.com.eduardosilva.infrastructure.mapper;


import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper( uses = CidadeMapper.class)
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    @Mapping(target = "id", expression = "java(endereco.id().value() )")
    EnderecoJpaEntity enderecoToEnderecoJpaEntity(Endereco endereco);

    @Mapping(target = "enderecoID", expression = "java(new EnderecoID(enderecoJpaEntity.getId()))")
    Endereco enderecoJpaEntityToendereco(EnderecoJpaEntity enderecoJpaEntity);

    default EnderecoID mapToEnderecoId(Long id) {
        return id != null ? new EnderecoID(id) : EnderecoID.empty(); // Retorna um enderecoId vazio se id for null
    }
}
