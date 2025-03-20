package br.com.eduardosilva.infrastructure.pessoa.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivoJpaEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "pes_id")
    @MapsId
    private PessoaJpaEntity pessoa;

    @Column(name = "se_matricula")
    private String matricula;

    public PessoaJpaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaJpaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
