package br.com.eduardosilva.infrastructure.pessoa.persistence;

import br.com.eduardosilva.domain.pessoa.*;
import br.com.eduardosilva.infrastructure.pessoa.models.ServidorEfetivoPorUnidadeIdCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<PessoaJpaEntity,Long> {
    @Query("""
            select p
            from PessoaJpaEntity p
            where
                p.pesNome = :nome and
                p.pesPai = :paiNome and
                p.pesMae = :maeNome and
                p.pesDataNascimento = :dtNascimento
        """)
    Optional<PessoaJpaEntity> existePessoa(String nome, String paiNome, String maeNome, LocalDate dtNascimento);


    @Query("""
                SELECT 
                   new br.com.eduardosilva.infrastructure.pessoa.models.ServidorEfetivoPorUnidadeIdCustom(
                                p,
                                u.nome
                            )
                FROM PessoaJpaEntity p
                JOIN p.servidorEfetivoJpaEntity
                JOIN p.lotacoes l 
                JOIN l.unidade u 
                WHERE u.id = :unidId
            """)
    Page<ServidorEfetivoPorUnidadeIdCustom> findServidoresEfetivosWithFotografiasByUnidade(@Param("unidId") Long unidId, Pageable pageable);


    @Query("""
                SELECT 
                     distinct new br.com.eduardosilva.domain.pessoa.EnderecoFuncionalPorNomeServidorPreview(
                        e.endTipoLogradouro, 
                        e.endLogradouro, 
                        e.endNumero, 
                        e.endBairro, 
                        c.uf ,
                        c.nome)                    
                FROM PessoaJpaEntity p
                    JOIN p.servidorEfetivoJpaEntity
                    JOIN p.lotacoes l 
                    JOIN l.unidade u
                    JOIN u.enderecos ue 
                    JOIN EnderecoJpaEntity e on ue.id.endereco = e.id
                    JOIN e.cidade c 
                WHERE p.pesNome LIKE concat('%', :nome  , '%') 
            """)
    Page<EnderecoFuncionalPorNomeServidorPreview> findEnderecoByNomeServidor(String nome, PageRequest page);

    @Query("""
            
            SELECT 
                new br.com.eduardosilva.domain.pessoa.ServidorTemporarioPreview(
                    p.pesId,
                    p.pesNome,
                    p.pesDataNascimento,
                    p.pesSexo,
                    p.pesMae,
                    p.pesPai,
                    st.stDataAdmissao,
                    st.stDataDemissao
                )
            FROM PessoaJpaEntity p
            JOIN p.servidorTemporarioJpaEntity st
            WHERE 
                (:nome is null or p.pesNome LIKE concat('%', :nome  , '%')) and
                (:stDataDemissao is null or st.stDataDemissao = :stDataDemissao) and
                (:stDataAdmissao is null or st.stDataAdmissao = :stDataAdmissao)
            """)
    Page<ServidorTemporarioPreview> findAllServidorTemporario(String nome, LocalDate stDataDemissao, LocalDate stDataAdmissao, PageRequest page);

    @Query("""
            SELECT 
                new br.com.eduardosilva.domain.pessoa.ServidorEfetivoPreview(
                    p.pesId,
                    p.pesNome,
                    p.pesDataNascimento,
                    p.pesSexo,
                    p.pesMae,
                    p.pesPai,
                    se.matricula
                )
            FROM PessoaJpaEntity p
            JOIN p.servidorEfetivoJpaEntity se
            WHERE 
                (:nome is null or p.pesNome LIKE concat('%', :nome  , '%')) and
                (:matricula is null or se.matricula like concat('%',:matricula,'%'))
            """)
    Page<ServidorEfetivoPreview> findAllServidorEfetivo(String nome, String matricula, PageRequest page);


    Optional<PessoaJpaEntity> findByServidorEfetivoJpaEntityMatricula(String matricula);
}
