package br.com.eduardosilva.infrastructure.unidade.persistence;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "unidade")
public class UnidadeJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "unid_seq")
    @SequenceGenerator( name = "unid_seq", sequenceName = "unidade_unid_id_seq", allocationSize = 1)
    @Column(name = "unid_id")
    private Long id;

    @Column(name = "unid_nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "unid_sigla", length = 20, nullable = false)
    private String sigla;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UnidadeEnderecoJpaEntity> enderecos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Set<UnidadeEnderecoJpaEntity> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<UnidadeEnderecoJpaEntity> enderecos) {
        this.enderecos = enderecos;
    }
}

