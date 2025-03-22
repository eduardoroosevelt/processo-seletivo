package br.com.eduardosilva.infrastructure.lotacao.persistence;

import br.com.eduardosilva.domain.lotacao.LotacaoPreview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}
