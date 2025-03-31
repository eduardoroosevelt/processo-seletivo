package br.com.eduardosilva.infrastructure.endereco.persistence;

import br.com.eduardosilva.infrastructure.cidade.persistence.CidadeJpaEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "endereco")
public class EnderecoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "endere_seq")
    @SequenceGenerator( name = "endere_seq", sequenceName = "endereco_end_id_seq", allocationSize = 1)
    @Column(name = "end_id")
    private Long id;

    @Column(name = "end_tipo_logradouro", length = 50, nullable = false)
    private String endTipoLogradouro;

    @Column(name = "end_logradouro", length = 200, nullable = false)
    private String endLogradouro;

    @Column(name = "end_numero", length = 200, nullable = false)
    private Integer endNumero;

    @Column(name = "end_bairro", length = 100, nullable = false)
    private String endBairro;

    @ManyToOne
    @JoinColumn(name = "cid_id")
    private CidadeJpaEntity cidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndTipoLogradouro() {
        return endTipoLogradouro;
    }

    public void setEndTipoLogradouro(String endTipoLogradouro) {
        this.endTipoLogradouro = endTipoLogradouro;
    }

    public String getEndLogradouro() {
        return endLogradouro;
    }

    public void setEndLogradouro(String endLogradouro) {
        this.endLogradouro = endLogradouro;
    }

    public Integer getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(Integer endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public CidadeJpaEntity getCidade() {
        return cidade;
    }

    public void setCidade(CidadeJpaEntity cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EnderecoJpaEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(endTipoLogradouro, that.endTipoLogradouro) && Objects.equals(endLogradouro, that.endLogradouro) && Objects.equals(endNumero, that.endNumero) && Objects.equals(endBairro, that.endBairro) && Objects.equals(cidade, that.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endTipoLogradouro, endLogradouro, endNumero, endBairro, cidade);
    }
}
