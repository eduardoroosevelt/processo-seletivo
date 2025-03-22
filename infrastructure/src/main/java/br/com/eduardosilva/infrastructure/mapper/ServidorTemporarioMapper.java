package br.com.eduardosilva.infrastructure.mapper;

import br.com.eduardosilva.domain.pessoa.ServidorTemporario;
import br.com.eduardosilva.infrastructure.pessoa.persistence.ServidorTemporarioJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServidorTemporarioMapper {
    ServidorTemporarioMapper INSTANCE = Mappers.getMapper(ServidorTemporarioMapper.class);

    @Mapping(target = "stDataAdmissao", source = "stDataAdmissao")
    @Mapping(target = "stDataDemissao", source = "stDataDemissao")
    ServidorTemporarioJpaEntity servidorTemporarioToServidorTemporarioJpaEntity(ServidorTemporario servidorTemporario);

    ServidorTemporario servidorTemporarioJpaEntityToServidorTemporario(ServidorTemporarioJpaEntity servidorTemporarioJpaEntity);
}
