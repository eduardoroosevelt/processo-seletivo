package br.com.eduardosilva.infrastructure.unidade.persistence;

import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import jakarta.persistence.*;

@Entity(name = "unidade_endereco")
@Table(name = "unidade_endereco")
public class UnidadeEnderecoJpaEntity {

    @EmbeddedId
    private UnidadeEnderecoJpaEntityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("unidade")
    @JoinColumn(name = "unid_id")
    private UnidadeJpaEntity unidade;


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
}
