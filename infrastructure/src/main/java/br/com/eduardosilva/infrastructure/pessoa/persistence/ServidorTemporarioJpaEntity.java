package br.com.eduardosilva.infrastructure.pessoa.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
public class ServidorTemporarioJpaEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "pes_id")
    private PessoaJpaEntity pessoa;

    @Column(name = "st_data_admissao")
    private LocalDate stDataAdmissao;

    @Column(name = "st_data_demissao")
    private LocalDate stDataDemissao;

    public PessoaJpaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaJpaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getStDataAdmissao() {
        return stDataAdmissao;
    }

    public void setStDataAdmissao(LocalDate stDataAdmissao) {
        this.stDataAdmissao = stDataAdmissao;
    }

    public LocalDate getStDataDemissao() {
        return stDataDemissao;
    }

    public void setStDataDemissao(LocalDate stDataDemissao) {
        this.stDataDemissao = stDataDemissao;
    }
}
