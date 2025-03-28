package br.com.eduardosilva.domain.lotacao;

import br.com.eduardosilva.domain.Entity;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivo;
import br.com.eduardosilva.domain.pessoa.ServidorTemporario;
import br.com.eduardosilva.domain.unidade.Unidade;

import java.time.LocalDate;

public class Lotacao extends Entity<LotacaoId> {

    private Pessoa pessoa;
    private Unidade unidade;
    private LocalDate lotDataLotacao;
    private LocalDate lotDataRemocao;
    private String lotPortaria;


    public Lotacao(LotacaoId lotacaoId, Pessoa pessoa, Unidade unidade, LocalDate lotDataLotacao, LocalDate lotDataRemocao, String lotPortaria) {
        super(lotacaoId);
        setPessoa(pessoa);
        setUnidade(unidade);
        setLotDataLotacao(lotDataLotacao);
        setLotDataRemocao(lotDataRemocao);
        setLotPortaria(lotPortaria);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public LocalDate getLotDataLotacao() {
        return lotDataLotacao;
    }

    public LocalDate getLotDataRemocao() {
        return lotDataRemocao;
    }

    public String getLotPortaria() {
        return lotPortaria;
    }

    private void setPessoa(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("Pessoa não pode ser nula");
        }
        this.pessoa = pessoa;
    }

    private void setUnidade(Unidade unidade) {
        if (unidade == null) {
            throw new IllegalArgumentException("Unidade não pode ser nula");
        }
        this.unidade = unidade;
    }

    private void setLotDataLotacao(LocalDate lotDataLotacao) {
        if (lotDataLotacao == null) {
            throw new IllegalArgumentException("Data de lotação não pode ser nula");
        }
        this.lotDataLotacao = lotDataLotacao;
    }

    private void setLotDataRemocao(LocalDate lotDataRemocao) {
        if (lotDataRemocao != null && lotDataRemocao.isBefore(this.lotDataLotacao) ) {
            DomainException.with("Data de remoção não pode ser anterior a data de lotação");
        }

        this.lotDataRemocao = lotDataRemocao;
    }

    private void setLotPortaria(String lotPortaria) {
        if (lotPortaria == null || lotPortaria.isEmpty() || lotPortaria.length() > 100) {
            throw new IllegalArgumentException("Portaria deve ser uma string não vazia com no máximo 100 caracteres");
        }
        this.lotPortaria = lotPortaria;
    }

    public Lotacao updatePessoa(Pessoa pessoa) {
        setPessoa(pessoa);
        return this;
    }

    public Lotacao updateUnidade(Unidade unidade) {
        setUnidade(unidade);
        return this;
    }

    public Lotacao updateLotPortaria(String lotPortaria) {
        setLotPortaria(lotPortaria);
        return this;
    }

    public  Lotacao updateDataLotacaoERemocao(LocalDate lotDataLotacao,LocalDate lotDataRemocao){

        if(lotDataRemocao != null && lotDataLotacao != null && lotDataLotacao.isAfter(lotDataRemocao)){
            DomainException.with("Data de admissão não pode ser posterior a data de demissão");
        }

        setLotDataLotacao(lotDataLotacao);
        setLotDataRemocao(lotDataRemocao);

        return this;
    }
}
