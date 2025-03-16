package br.com.eduardosilva.infrastructure.cidade.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class CidadeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "cidade_seq")
    @SequenceGenerator( name = "cidade_seq", sequenceName = "cidade_cid_id_seq", allocationSize = 1)
    @Column(name = "cid_id")
    private Long id;

    @Column(name = "cid_nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "cid_uf", length = 2, nullable = false)
    private String uf;



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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
