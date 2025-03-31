package br.com.eduardosilva.infrastructure.unidade.persistence;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.unidade.Unidade;
import br.com.eduardosilva.domain.unidade.UnidadePreview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UnidadeRepository extends JpaRepository<UnidadeJpaEntity,Long> {

    @Query("""
            select 
                 new br.com.eduardosilva.domain.unidade.UnidadePreview(
                   u.id,
                   u.nome,
                   u.sigla
                )
            from UnidadeJpaEntity u
            where 
                    ( :nomeUni is null  or :nomeUni is not null and  UPPER(u.nome) like  concat('%', :nomeUni, '%') )  
               and
                    ( :siglaUni is null or :siglaUni is not null and UPPER(u.sigla) like  concat('%', :siglaUni, '%')  )
            """)
    Page<UnidadePreview> findAlls(
            @Param("nomeUni")  String nomeUni,
            @Param("siglaUni") String siglaUni,
            Pageable pageable);

    @Query("""
            select u from UnidadeJpaEntity u
            where 
                UPPER(u.nome) = :nome
                and UPPER(u.sigla) = :sigla
            """)
    Optional<UnidadeJpaEntity> existeUnidade(String nome, String sigla);
}
