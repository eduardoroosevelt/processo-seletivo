package br.com.eduardosilva.infrastructure.mapper;

import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.unidade.Unidade;
import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import br.com.eduardosilva.infrastructure.unidade.persistence.UnidadeEnderecoJpaEntity;
import br.com.eduardosilva.infrastructure.unidade.persistence.UnidadeEnderecoJpaEntityId;
import br.com.eduardosilva.infrastructure.unidade.persistence.UnidadeJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper( uses = EnderecoMapper.class)
public interface UnidadeMapper {
    UnidadeMapper INSTANCE = Mappers.getMapper(UnidadeMapper.class);

    @Mapping(target = "id", expression = "java(unidade.id().value() )")
    @Mapping(target = "enderecos", expression = "java(mapEnderecos(unidade.getEnderecos(), unidadeJpaEntity ) )")
    UnidadeJpaEntity unidadeToUnidadeJpaEntity(Unidade unidade);

    @Mapping(target = "unidadeId", expression = "java(new UnidadeId(unidadeJpaEntity.getId()))")
    @Mapping(target = "enderecos", expression = "java(mapEnderecosId(unidadeJpaEntity.getEnderecos()) )")
    Unidade unidadeJpaEntityToUnidade(UnidadeJpaEntity unidadeJpaEntity);

    default Set<Endereco> mapEnderecosId(List<UnidadeEnderecoJpaEntity> enderecos){
        return enderecos
                .stream()
                .map(end ->
                        new Endereco(
                                new EnderecoID(end.getId().getEndereco()),
                                end.endereco().getEndTipoLogradouro(),
                                end.endereco().getEndLogradouro(),
                                end.endereco().getEndNumero(),
                                end.endereco().getEndBairro(),
                                CidadeMapper.INSTANCE.cidadeJpaEntityToCidade(end.endereco().getCidade())
                        ))
                .collect(Collectors.toSet());
    }

    default List<Endereco> mapEnderecosId(Set<UnidadeEnderecoJpaEntity> enderecos){
        return enderecos
                .stream()
                .map(end ->
                        new Endereco(
                                new EnderecoID(end.getId().getEndereco()),
                                end.endereco().getEndTipoLogradouro(),
                                end.endereco().getEndLogradouro(),
                                end.endereco().getEndNumero(),
                                end.endereco().getEndBairro(),
                                CidadeMapper.INSTANCE.cidadeJpaEntityToCidade(end.endereco().getCidade())
                        ))
                .collect(Collectors.toList());
    }

    default Set<UnidadeEnderecoJpaEntity> mapEnderecos(List<Endereco> enderecos, UnidadeJpaEntity unidade) {
        Set<UnidadeEnderecoJpaEntity> uniEnderecos = new HashSet<>();
        if (enderecos != null) {
            for (Endereco endereco : enderecos) {
                // Definindo o ID do endereço


                UnidadeEnderecoJpaEntityId unidadeEnderecoJpaEntityId = new UnidadeEnderecoJpaEntityId();
                unidadeEnderecoJpaEntityId.setendereco(endereco.id().value());
                unidadeEnderecoJpaEntityId.setunidade(unidade.getId());

                UnidadeEnderecoJpaEntity unidadeEndereco = new UnidadeEnderecoJpaEntity();
                unidadeEndereco.setUnidade(unidade);
                unidadeEndereco.setEndereco(EnderecoMapper.INSTANCE.enderecoToEnderecoJpaEntity(endereco));
                unidadeEndereco.setId(unidadeEnderecoJpaEntityId);

                uniEnderecos.add(unidadeEndereco);
            }
        }
        return uniEnderecos;
    }


}
