package br.com.eduardosilva.infrastructure.pessoa.persistence;

import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_endereco")
public class PessoaEnderecoJpaEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "pes_id")
    private PessoaJpaEntity pessoa;

    @Id
    @ManyToOne
    @JoinColumn(name = "end_id")
    private EnderecoJpaEntity endereco;

    public PessoaJpaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaJpaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public EnderecoJpaEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoJpaEntity endereco) {
        this.endereco = endereco;
    }
}
