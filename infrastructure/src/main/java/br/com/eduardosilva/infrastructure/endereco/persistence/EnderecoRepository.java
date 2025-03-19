package br.com.eduardosilva.infrastructure.endereco.persistence;

import br.com.eduardosilva.domain.endereco.EnderecoPreview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<EnderecoJpaEntity,Long> {

    @Query("""
               select 
                 new br.com.eduardosilva.domain.endereco.EnderecoPreview(
                    e.id,
                    e.endTipoLogradouro,
                    e.endLogradouro,
                    e.endNumero,
                    e.endBairro,
                    e.cidade.nome
                 )
               from EnderecoJpaEntity e
               where 
                   (:endBairro is null or UPPER(e.endBairro) like concat('%', :endBairro , '%')  ) and
                   (:endLogradouro is null or UPPER(e.endLogradouro) like concat('%', :endLogradouro , '%') ) and
                   (:endTipoLogradouro is null or UPPER(e.endTipoLogradouro) like  concat('%', :endTipoLogradouro  , '%') ) and
                   (:endNumero is null or e.endNumero = :endNumero) and
                   (:cidadeId is null or e.cidade.id = :cidadeId )
            """)
    Page<EnderecoPreview> findAll(String endBairro, String endLogradouro, String endTipoLogradouro, Integer endNumero, Long cidadeId, Pageable page);

    @Query(value = "select e.id from EnderecoJpaEntity e where e.id in :ids")
    List<Long> existsByIds(List<Long> ids);
}
