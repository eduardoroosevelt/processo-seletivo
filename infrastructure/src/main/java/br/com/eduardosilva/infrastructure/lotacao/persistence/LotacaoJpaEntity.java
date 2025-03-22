package br.com.eduardosilva.infrastructure.lotacao.persistence;

import br.com.eduardosilva.infrastructure.pessoa.persistence.PessoaJpaEntity;
import br.com.eduardosilva.infrastructure.unidade.persistence.UnidadeJpaEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
public class LotacaoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long lotId;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private PessoaJpaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "unid_id")
    private UnidadeJpaEntity unidade;

    @Column(name = "lot_data_lotacao", nullable = false)
    private LocalDate lotDataLotacao;

    @Column(name = "lot_data_remocao", nullable = false)
    private LocalDate lotDataRemocao;

    @Column(name = "lot_portaria", nullable = false)
    private String lotPortaria;

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public PessoaJpaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaJpaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public UnidadeJpaEntity getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeJpaEntity unidade) {
        this.unidade = unidade;
    }

    public LocalDate getLotDataLotacao() {
        return lotDataLotacao;
    }

    public void setLotDataLotacao(LocalDate lotDataLotacao) {
        this.lotDataLotacao = lotDataLotacao;
    }

    public LocalDate getLotDataRemocao() {
        return lotDataRemocao;
    }

    public void setLotDataRemocao(LocalDate lotDataRemocao) {
        this.lotDataRemocao = lotDataRemocao;
    }

    public String getLotPortaria() {
        return lotPortaria;
    }

    public void setLotPortaria(String lotPortaria) {
        this.lotPortaria = lotPortaria;
    }
}
