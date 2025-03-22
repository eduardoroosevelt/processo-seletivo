package br.com.eduardosilva.domain.endereco;

import br.com.eduardosilva.domain.Entity;
import br.com.eduardosilva.domain.cidade.Cidade;
import br.com.eduardosilva.domain.cidade.CidadeId;

public class Endereco  extends Entity<EnderecoID> {

    private String endTipoLogradouro;
    private String endLogradouro;
    private Integer endNumero;
    private String endBairro;
    private Cidade cidade;

    public Endereco(EnderecoID enderecoID,
                    String endTipoLogradouro,
                    String endLogradouro,
                    Integer endNumero,
                    String endBairro,
                    Cidade cidade) {

        super(enderecoID);
        setEndTipoLogradouro(endTipoLogradouro);
        setEndLogradouro(endLogradouro);
        setEndNumero(endNumero);
        setEndBairro(endBairro);
        setCidade(cidade);
    }

    public String getEndTipoLogradouro() {
        return endTipoLogradouro;
    }
    public String getEndLogradouro() {
        return endLogradouro;
    }

    public Integer getEndNumero() {
        return endNumero;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    private void setEndTipoLogradouro(String endTipoLogradouro) {
        this.endTipoLogradouro = endTipoLogradouro;
    }

    private void setEndLogradouro(String endLogradouro) {
        this.endLogradouro = endLogradouro;
    }

    private void setEndNumero(Integer endNumero) {
        this.endNumero = endNumero;
    }

    private void setEndBairro(String endBairro) {
        assertArgumentMaxLength(endBairro,200,"Bairro não pode ser maior que 200 caracteeres");
        this.endBairro = endBairro;
    }

    private void setCidade(Cidade cidade) {
        assertArgumentNotNull(cidade,"Cidade não pode ser vazio");

        this.cidade = cidade;
    }

    public Endereco updateEndBairro(String endBairro) {
        if (endBairro != null ) {
            setEndBairro( endBairro);
        }
        return this;
    }

    public Endereco updateEndNumero(Integer endNumero){
        if (endNumero != null) {
            setEndNumero(endNumero);
        }
        return this;
    }

    public Endereco updateEndLogradouro(String endLogradouro){
        if (endLogradouro != null ) {
            setEndLogradouro(endLogradouro);
        }
        return this;
    }

    public Endereco updateEndTipoLogradouro(String endTipoLogradouro){
        if (endTipoLogradouro != null ) {
            setEndTipoLogradouro(endTipoLogradouro);
        }
        return this;
    }

    public Endereco updateCidade(Cidade cidade) {
        if (id != null ) {
            setCidade(cidade);
        }
        return this;
    }
}
