package br.com.eduardosilva.infrastructure.unidade.persistence;

import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "unidade_endereco")
@Table(name = "unidade_endereco")
public class UnidadeEnderecoJpaEntity {

    @EmbeddedId
    private UnidadeEnderecoJpaEntityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("unidade")
    @JoinColumn(name = "unid_id")
    private UnidadeJpaEntity unidade;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @MapsId("endereco")
    @JoinColumn(name = "end_id")
    private EnderecoJpaEntity endereco;

    public UnidadeJpaEntity getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeJpaEntity unidade) {
        this.unidade = unidade;
    }

    public UnidadeEnderecoJpaEntityId getId() {
        return id;
    }

    public void setId(UnidadeEnderecoJpaEntityId id) {
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
        if (!(o instanceof UnidadeEnderecoJpaEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endereco);
    }
}
