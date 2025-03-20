package br.com.eduardosilva.infrastructure.mapper;

import br.com.eduardosilva.domain.pessoa.ServidorEfetivo;
import br.com.eduardosilva.infrastructure.pessoa.persistence.ServidorEfetivoJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServidorEfetivoMapper {
    ServidorEfetivoMapper INSTANCE = Mappers.getMapper(ServidorEfetivoMapper.class);

    @Mapping(target = "matricula", source = "matricula")
    ServidorEfetivoJpaEntity servidorEfetivoToServidorEfetivoJpaEntity(ServidorEfetivo servidorEfetivo);

    ServidorEfetivo servidorEfetivoJpaEntityToServidorEfetivo(ServidorEfetivoJpaEntity servidorEfetivoJpaEntity);
}
