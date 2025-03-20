package br.com.eduardosilva.infrastructure.mapper;


import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.infrastructure.pessoa.persistence.PessoaJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper( uses = {EnderecoMapper.class, ServidorEfetivoMapper.class})
public interface PessoaMapper {
    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(target = "pesId", expression = "java(pessoa.id().value() )")
    PessoaJpaEntity pessoaToPessoaJpaEntity(Pessoa pessoa);

    @Mapping(target = "pessoaId", expression = "java(new PessoaId(pessoaJpaEntity.getPesId()))")
    @Mapping(target = "servidorEfetivo", source = "servidorEfetivoJpaEntity")
    Pessoa pessoaJpaEntityToPessoa(PessoaJpaEntity pessoaJpaEntity);

}
