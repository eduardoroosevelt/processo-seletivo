package br.com.eduardosilva.infrastructure.pessoa.persistence;

import br.com.eduardosilva.domain.pessoa.EnderecoFuncionalPorNomeServidorPreview;
import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivoPorUnidadeIdPreview;
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
}
