package br.com.eduardosilva.domain.cidade;

import br.com.eduardosilva.domain.AssertionConcern;
import br.com.eduardosilva.domain.Entity;

public class Cidade extends Entity<CidadeId> {

    private String nome;
    private String uf;

    public  Cidade(
            CidadeId cidadeId,
            String nome,
            String uf) {
        super(cidadeId);
        this.setNome(nome);
        this.setUf(uf);
    }


    public String getNome() {
        return nome;
    }

    public String getUf() {
        return uf;
    }

    public void setNome(String nome) {
        assertArgumentNotNull(nome,"Nome da cidade não deve ser nul");
        assertArgumentNotEmpty(nome,"Nome da cidade não deve ser vazio");
        this.nome = nome;
    }

    public void setUf(String uf) {
        assertArgumentNotNull(uf,"Nome não deve ser nul");
        assertArgumentNotEmpty(uf,"Nome não deve ser vazio");
        this.uf = uf;
    }
}
