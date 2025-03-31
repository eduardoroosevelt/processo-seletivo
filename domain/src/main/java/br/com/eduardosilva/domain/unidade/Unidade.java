package br.com.eduardosilva.domain.unidade;

import br.com.eduardosilva.domain.Entity;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoID;

import java.util.*;

public class Unidade extends Entity<UnidadeId> {

    private String nome;
    private String sigla;
    private List<Endereco> enderecos;

    public Unidade(UnidadeId unidadeId,
                    String nome,
                    String sigla,
                   List<Endereco> enderecos
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

    public List<Endereco> getEnderecos() {
        return enderecos != null ? Collections.unmodifiableList(enderecos) : Collections.emptyList();
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

    private void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos != null ? new ArrayList<>(enderecos) : Collections.emptyList();
    }

    public Unidade updateNome(String nome) {
        setNome(nome);
        return this;
    }

    public Unidade updateSigla(String sigla) {
        setSigla(sigla);
        return this;
    }

    public Unidade updateEnderecos(List<Endereco> enderecos) {
        setEnderecos(enderecos);
        return this;
    }
}
