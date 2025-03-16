package br.com.eduardosilva.infrastructure.unidade.persistence;

import java.util.Objects;

public class UnidadeEnderecoJpaEntityId {
    private Long unidade;
    private Long endereco;

    public Long getendereco() {
        return endereco;
    }

    public void setendereco(Long endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnidadeEnderecoJpaEntityId that = (UnidadeEnderecoJpaEntityId) o;
        return Objects.equals(unidade, that.unidade) && Objects.equals(endereco, that.endereco);
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
