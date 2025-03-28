package br.com.eduardosilva.domain.unidade;

import br.com.eduardosilva.domain.Entity;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.endereco.EnderecoID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Unidade extends Entity<UnidadeId> {

    private String nome;
    private String sigla;
    private Set<EnderecoID> enderecos;

    public Unidade(UnidadeId unidadeId,
                    String nome,
                    String sigla,
                   Set<EnderecoID> enderecos
                    ) {

        super(unidadeId);
        setNome(nome);
        setSigla(sigla);
        setEnderecos(enderecos);
    }


    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public Set<EnderecoID> getEnderecos() {
        return enderecos != null ? Collections.unmodifiableSet(enderecos) : Collections.emptySet();
    }

    private void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da unidade não pode ser vazio.");
        }
        if (nome.length() > 200) {
            throw new IllegalArgumentException("O nome da unidade deve ter no máximo 200 caracteres.");
        }
        this.nome = nome;
    }

    private void setSigla(String sigla) {
        if (sigla == null || sigla.trim().isEmpty()) {
            throw new IllegalArgumentException("A sigla da unidade não pode ser vazia.");
        }
        if (sigla.length() > 20) {
            throw new IllegalArgumentException("A sigla da unidade deve ter no máximo 20 caracteres.");
        }
        this.sigla = sigla;
    }

    private void setEnderecos(Set<EnderecoID> enderecos) {
        this.enderecos = enderecos != null ? new HashSet<>(enderecos) : Collections.emptySet();
    }

    public Unidade updateNome(String nome) {
        setNome(nome);
        return this;
    }

    public Unidade updateSigla(String sigla) {
        setSigla(sigla);
        return this;
    }

    public Unidade updateEnderecos(Set<EnderecoID> enderecos) {
        setEnderecos(enderecos);
        return this;
    }
}
