package br.com.eduardosilva.infrastructure.pessoa.persistence;

import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import jakarta.persistence.*;

@Entity(name = "pessoa_endereco")
@Table(name = "pessoa_endereco")
public class PessoaEnderecoJpaEntity {

    @EmbeddedId
    private PessoaEnderecoJpaEntityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pessoa")
    @JoinColumn(name = "pes_id")
    private PessoaJpaEntity pessoa;

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
}
