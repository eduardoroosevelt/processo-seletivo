package br.com.eduardosilva.infrastructure.pessoa.persistence;

import br.com.eduardosilva.domain.pessoa.PessoaFoto;
import br.com.eduardosilva.infrastructure.lotacao.persistence.LotacaoJpaEntity;
import br.com.eduardosilva.infrastructure.unidade.persistence.UnidadeEnderecoJpaEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "pessoa")
public class PessoaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "pessoa_pes_id_seq")
    @SequenceGenerator( name = "pessoa_pes_id_seq", sequenceName = "pessoa_pes_id_seq", allocationSize = 1)
    @Column(name = "pes_id")
    private Long pesId;

    @Column(name = "pes_nome", length = 200, nullable = false)
    private String pesNome;

    @Column(name = "pes_data_nascimento", nullable = false)
    private LocalDate pesDataNascimento;

    @Column(name = "pes_sexo", length = 9, nullable = false)
    private String pesSexo;

    @Column(name = "pes_mae", length = 200, nullable = false)
    private String pesMae;

    @Column(name = "pes_pai", length = 200, nullable = false)
    private String pesPai;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private ServidorEfetivoJpaEntity servidorEfetivoJpaEntity;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private ServidorTemporarioJpaEntity servidorTemporarioJpaEntity;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PessoaEnderecoJpaEntity> enderecos;

    @OneToMany(mappedBy = "pessoa")
    private Set<LotacaoJpaEntity> lotacoes;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PessoaFotoJpaEntity> fotos;

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public LocalDate getPesDataNascimento() {
        return pesDataNascimento;
    }

    public void setPesDataNascimento(LocalDate pesDataNascimento) {
        this.pesDataNascimento = pesDataNascimento;
    }

    public String getPesSexo() {
        return pesSexo;
    }

    public void setPesSexo(String pesSexo) {
        this.pesSexo = pesSexo;
    }

    public String getPesMae() {
        return pesMae;
    }

    public void setPesMae(String pesMae) {
        this.pesMae = pesMae;
    }

    public String getPesPai() {
        return pesPai;
    }

    public void setPesPai(String pesPai) {
        this.pesPai = pesPai;
    }

    public ServidorEfetivoJpaEntity getServidorEfetivoJpaEntity() {
        return servidorEfetivoJpaEntity;
    }

    public void setServidorEfetivoJpaEntity(ServidorEfetivoJpaEntity servidorEfetivoJpaEntity) {
        this.servidorEfetivoJpaEntity = servidorEfetivoJpaEntity;
    }

    public Set<PessoaEnderecoJpaEntity> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<PessoaEnderecoJpaEntity> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<PessoaFotoJpaEntity> getFotos() {
        return fotos;
    }

    public void setFotos(Set<PessoaFotoJpaEntity> fotos) {
        this.fotos = fotos;
    }

    public ServidorTemporarioJpaEntity getServidorTemporarioJpaEntity() {
        return servidorTemporarioJpaEntity;
    }

    public void setServidorTemporarioJpaEntity(ServidorTemporarioJpaEntity servidorTemporarioJpaEntity) {
        this.servidorTemporarioJpaEntity = servidorTemporarioJpaEntity;
    }

    public Set<LotacaoJpaEntity> getLotacoes() {
        return lotacoes;
    }

    public void setLotacoes(Set<LotacaoJpaEntity> lotacoes) {
        this.lotacoes = lotacoes;
    }
}
