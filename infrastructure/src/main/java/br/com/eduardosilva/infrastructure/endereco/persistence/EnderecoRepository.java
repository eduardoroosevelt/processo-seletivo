package br.com.eduardosilva.infrastructure.endereco.persistence;

import br.com.eduardosilva.domain.endereco.EnderecoPreview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
                   (:endBairro is null or UPPER(e.endBairro) like :endBairro ) and
                   (:endLogradouro is null or UPPER(e.endLogradouro) like :endLogradouro ) and
                   (:endTipoLogradouro is null or UPPER(e.endTipoLogradouro) like :endTipoLogradouro ) and
                   (:endNumero is null or e.endNumero = :endNumero) and
                   (:cidadeId is null or e.cidade.id = :cidadeId )
            """)
    Page<EnderecoPreview> findAll(String endBairro, String endLogradouro, String endTipoLogradouro, Integer endNumero, Long cidadeId, Pageable page);
}
