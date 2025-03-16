package br.com.eduardosilva.infrastructure.pessoa.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivoJpaEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "pes_id")
    private PessoaJpaEntity pessoa;

    @Column(name = "st_matricula")
    private String stMatricula;

    public PessoaJpaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaJpaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public String getStMatricula() {
        return stMatricula;
    }

    public void setStMatricula(String stMatricula) {
        this.stMatricula = stMatricula;
    }
}
