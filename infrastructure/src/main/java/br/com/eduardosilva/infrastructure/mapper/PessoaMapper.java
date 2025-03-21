package br.com.eduardosilva.infrastructure.mapper;


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
import java.util.Set;
import java.util.stream.Collectors;

@Mapper( uses = {EnderecoMapper.class, ServidorEfetivoMapper.class})
public interface PessoaMapper {
    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(target = "pesId", expression = "java(pessoa.id().value() )")
    @Mapping(target = "enderecos", expression = "java(mapEnderecos(pessoa.getEnderecos(), pessoaJpaEntity ) )")
    PessoaJpaEntity pessoaToPessoaJpaEntity(Pessoa pessoa);

    @Mapping(target = "pessoaId", expression = "java(new PessoaId(pessoaJpaEntity.getPesId()))")
    @Mapping(target = "servidorEfetivo", source = "servidorEfetivoJpaEntity")
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

    default Set<EnderecoID> mapEnderecosId(Set<PessoaEnderecoJpaEntity> enderecos){
        return enderecos
                .stream()
                .map(end -> new EnderecoID(end.getId().getEndereco()))
                .collect(Collectors.toSet());
    }

    default Set<PessoaEnderecoJpaEntity> mapEnderecos(Set<EnderecoID> enderecos, PessoaJpaEntity pessoa) {
        Set<PessoaEnderecoJpaEntity> pesEnderecos = new HashSet<>();
        if (enderecos != null) {
            for (EnderecoID enderecoId : enderecos) {
                // Definindo o ID do endereço
                PessoaEnderecoJpaEntity pessoaEndereco = new PessoaEnderecoJpaEntity();
                pessoaEndereco.setPessoa(pessoa);

                PessoaEnderecoJpaEntityId pessoaEnderecoJpaEntityId = new PessoaEnderecoJpaEntityId();
                pessoaEnderecoJpaEntityId.setEndereco(enderecoId.value());
                pessoaEnderecoJpaEntityId.setPessoa(pessoa.getPesId());

                pessoaEndereco.setId(pessoaEnderecoJpaEntityId);
                pesEnderecos.add(pessoaEndereco);
            }
        }
        return pesEnderecos;
    }
}
