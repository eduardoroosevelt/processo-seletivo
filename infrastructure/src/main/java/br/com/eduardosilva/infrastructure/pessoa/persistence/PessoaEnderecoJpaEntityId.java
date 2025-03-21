package br.com.eduardosilva.infrastructure.pessoa.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PessoaEnderecoJpaEntityId implements Serializable {

    @Column(name = "pes_id", nullable = false)
    private Long pessoa;

    @Column(name = "end_id", nullable = false)
    private Long endereco;

    public Long getPessoa() {
        return pessoa;
    }

    public void setPessoa(Long pessoa) {
        this.pessoa = pessoa;
    }

    public Long getEndereco() {
        return endereco;
    }

    public void setEndereco(Long endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PessoaEnderecoJpaEntityId that = (PessoaEnderecoJpaEntityId) o;
        return Objects.equals(pessoa, that.pessoa) && Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pessoa, endereco);
    }
}
