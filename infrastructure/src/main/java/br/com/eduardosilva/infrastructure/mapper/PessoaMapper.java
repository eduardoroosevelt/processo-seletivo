package br.com.eduardosilva.infrastructure.mapper;


import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.pessoa.PessoaFoto;
import br.com.eduardosilva.infrastructure.pessoa.persistence.PessoaEnderecoJpaEntity;
import br.com.eduardosilva.infrastructure.pessoa.persistence.PessoaEnderecoJpaEntityId;
import br.com.eduardosilva.infrastructure.pessoa.persistence.PessoaFotoJpaEntity;
import br.com.eduardosilva.infrastructure.pessoa.persistence.PessoaJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper( uses = {EnderecoMapper.class, ServidorEfetivoMapper.class, ServidorTemporarioMapper.class})
public interface PessoaMapper {
    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(target = "pesId", expression = "java(pessoa.id().value() )")
    @Mapping(target = "enderecos", expression = "java(mapEnderecos(pessoa.getEnderecos(), pessoaJpaEntity ) )")
    PessoaJpaEntity pessoaToPessoaJpaEntity(Pessoa pessoa);

    @Mapping(target = "pessoaId", expression = "java(new PessoaId(pessoaJpaEntity.getPesId()))")
    @Mapping(target = "updateServidorEfetivo", source = "servidorEfetivoJpaEntity")
    @Mapping(target = "updateServidorTemporario", source = "servidorTemporarioJpaEntity")
    @Mapping(target = "enderecos", expression = "java(mapEnderecosId(pessoaJpaEntity.getEnderecos()) )")
    @Mapping(target = "fotos", expression = "java(mapPessoaFoto(pessoaJpaEntity.getFotos()) )")
    Pessoa pessoaJpaEntityToPessoa(PessoaJpaEntity pessoaJpaEntity);

    default Set<PessoaFoto> mapPessoaFoto(Set<PessoaFotoJpaEntity> fotos){
        return fotos
                .stream()
                .map(ft -> new PessoaFoto(
                        ft.getFpId(),
                        ft.getFpData(),
                        ft.getFpBucket(),
                        ft.getFpHash()
                ))
                .collect(Collectors.toSet());
    }

    default List<Endereco> mapEnderecosId(Set<PessoaEnderecoJpaEntity> enderecos){
        return enderecos
                .stream()
                .map(end -> new Endereco(
                        new EnderecoID(end.getId().getEndereco()),
                        end.endereco().getEndTipoLogradouro(),
                        end.endereco().getEndLogradouro(),
                        end.endereco().getEndNumero(),
                        end.endereco().getEndBairro(),
                        CidadeMapper.INSTANCE.cidadeJpaEntityToCidade(end.endereco().getCidade())
                ))
                .collect(Collectors.toList());
    }

    default Set<Endereco> mapEnderecosId(List<PessoaEnderecoJpaEntity> enderecos){
        return enderecos
                .stream()
                .map(end -> new Endereco(
                        new EnderecoID(end.getId().getEndereco()),
                        end.endereco().getEndTipoLogradouro(),
                        end.endereco().getEndLogradouro(),
                        end.endereco().getEndNumero(),
                        end.endereco().getEndBairro(),
                        CidadeMapper.INSTANCE.cidadeJpaEntityToCidade(end.endereco().getCidade())
                ))
                .collect(Collectors.toSet());
    }

    default Set<PessoaEnderecoJpaEntity> mapEnderecos(List<Endereco> enderecos, PessoaJpaEntity pessoa) {
        Set<PessoaEnderecoJpaEntity> pesEnderecos = new HashSet<>();

        if (enderecos != null) {
            for (Endereco endereco : enderecos) {
                PessoaEnderecoJpaEntity pessoaEndereco = new PessoaEnderecoJpaEntity();
                pessoaEndereco.setPessoa(pessoa);
                pessoaEndereco.setEndereco(EnderecoMapper.INSTANCE.enderecoToEnderecoJpaEntity(endereco));

                PessoaEnderecoJpaEntityId pessoaEnderecoJpaEntityId = new PessoaEnderecoJpaEntityId();
                pessoaEnderecoJpaEntityId.setPessoa(pessoa.getPesId());
                pessoaEnderecoJpaEntityId.setEndereco(endereco.id().value());

                pessoaEndereco.setId(pessoaEnderecoJpaEntityId);

                pesEnderecos.add(pessoaEndereco);
            }
        }

        return pesEnderecos;
    }


}
