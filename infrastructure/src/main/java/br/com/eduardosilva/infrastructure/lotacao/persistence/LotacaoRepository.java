package br.com.eduardosilva.infrastructure.lotacao.persistence;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.lotacao.LotacaoPreview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface LotacaoRepository extends JpaRepository<LotacaoJpaEntity, Long> {
    @Query("""
            select 
                new br.com.eduardosilva.domain.lotacao.LotacaoPreview(
                 l.lotId,
                        l.lotDataLotacao,
                        l.lotDataRemocao,
                        l.lotPortaria,
                        l.pessoa.pesNome,
                        l.unidade.nome
                        )  
            from LotacaoJpaEntity l
            
            where 
                (:unidId is null or l.unidade.id = :unidId)   
                and
                (:lotPortaria is null or UPPER(l.lotPortaria) like concat('%', :lotPortaria , '%')  )  
            """)
    Page<LotacaoPreview> findAll(String lotPortaria, Long unidId, PageRequest page);


    @Query("""
                select l from LotacaoJpaEntity l
                where 
                    l.pessoa.id = :pesId
                    and l.unidade.id = :uniId
                    and l.lotDataLotacao = :lotDataLotacao
                    and l.lotDataRemocao = :lotDataRemocao
                    and UPPER(l.lotPortaria) = UPPER(:lotPortaria)
            """)
    Optional<LotacaoJpaEntity> existeLotacao(Long pesId, Long uniId, LocalDate lotDataLotacao, LocalDate lotDataRemocao, String lotPortaria);

    Boolean existsByPessoa_PesId(Long value);
}
