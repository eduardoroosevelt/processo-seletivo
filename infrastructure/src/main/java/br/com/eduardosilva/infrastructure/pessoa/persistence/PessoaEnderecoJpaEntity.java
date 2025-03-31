package br.com.eduardosilva.infrastructure.pessoa.persistence;

import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "pessoa_endereco")
@Table(name = "pessoa_endereco")
public class PessoaEnderecoJpaEntity {

    @EmbeddedId
    private PessoaEnderecoJpaEntityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pessoa")
    @JoinColumn(name = "pes_id")
    private PessoaJpaEntity pessoa;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @MapsId("endereco")
    @JoinColumn(name = "end_id")
    private EnderecoJpaEntity endereco;

    public PessoaJpaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaJpaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaEnderecoJpaEntityId getId() {
        return id;
    }

    public void setId(PessoaEnderecoJpaEntityId id) {
        this.id = id;
    }

    public EnderecoJpaEntity endereco() {
        return endereco;
    }

    public void setEndereco(EnderecoJpaEntity endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PessoaEnderecoJpaEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endereco);
    }
}
