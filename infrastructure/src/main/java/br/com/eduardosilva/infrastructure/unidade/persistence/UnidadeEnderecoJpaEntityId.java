package br.com.eduardosilva.infrastructure.unidade.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UnidadeEnderecoJpaEntityId implements Serializable {

    @Column(name = "unid_id", nullable = false)
    private Long unidade;

    @Column(name = "end_id", nullable = false)
    private Long endereco;

    public Long getEndereco() {
        return endereco;
    }

    public void setendereco(Long endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnidadeEnderecoJpaEntityId)) return false;
        UnidadeEnderecoJpaEntityId that = (UnidadeEnderecoJpaEntityId) o;
        return Objects.equals(unidade, that.unidade) &&
                Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unidade, endereco);
    }

    public Long getunidade() {
        return unidade;
    }

    public void setunidade(Long unidade) {
        this.unidade = unidade;
    }


}
