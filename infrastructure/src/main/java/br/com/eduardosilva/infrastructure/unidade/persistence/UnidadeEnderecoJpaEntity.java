package br.com.eduardosilva.infrastructure.unidade.persistence;

import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "unidade_endereco")
@IdClass(UnidadeEnderecoJpaEntityId.class)
public class UnidadeEnderecoJpaEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "unid_id")
    private UnidadeJpaEntity unidade;

    @Id
    @ManyToOne
    @JoinColumn(name = "end_id")
    private EnderecoJpaEntity endereco;

    public UnidadeJpaEntity getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeJpaEntity unidade) {
        this.unidade = unidade;
    }

    public EnderecoJpaEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoJpaEntity endereco) {
        this.endereco = endereco;
    }
}
