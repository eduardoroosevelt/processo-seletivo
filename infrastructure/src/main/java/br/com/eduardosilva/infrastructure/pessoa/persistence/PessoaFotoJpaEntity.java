package br.com.eduardosilva.infrastructure.pessoa.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
public class PessoaFotoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "pessoa_pes_id_seq")
    @SequenceGenerator( name = "pessoa_pes_id_seq", sequenceName = "pessoa_pes_id_seq", allocationSize = 1)
    @Column(name = "fp_id")
    private Long fpId;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private PessoaJpaEntity pessoa;

    @Column(name = "fp_data", nullable = false)
    private LocalDate fpData;

    @Column(name = "fp_bucket", length = 50, nullable = false)
    private String fpBucket;

    @Column(name = "fp_hash", length = 50, nullable = false)
    private String fpHash;

    public Long getFpId() {
        return fpId;
    }

    public void setFpId(Long fpId) {
        this.fpId = fpId;
    }

    public PessoaJpaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaJpaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getFpData() {
        return fpData;
    }

    public void setFpData(LocalDate fpData) {
        this.fpData = fpData;
    }

    public String getFpBucket() {
        return fpBucket;
    }

    public void setFpBucket(String fpBucket) {
        this.fpBucket = fpBucket;
    }

    public String getFpHash() {
        return fpHash;
    }

    public void setFpHash(String fpHash) {
        this.fpHash = fpHash;
    }
}
