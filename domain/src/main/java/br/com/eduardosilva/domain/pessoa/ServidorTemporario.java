package br.com.eduardosilva.domain.pessoa;

import br.com.eduardosilva.domain.exceptions.DomainException;

import java.time.LocalDate;

public class ServidorTemporario {

    private LocalDate stDataAdmissao;
    private LocalDate stDataDemissao;

    public ServidorTemporario(LocalDate stDataAdmissao, LocalDate stDataDemissao) {
        setStDataAdmissao(stDataAdmissao);
        setStDataDemissao(stDataDemissao);

    }

    public LocalDate getStDataAdmissao() {
        return stDataAdmissao;
    }

    public LocalDate getStDataDemissao() {
        return stDataDemissao;
    }

    private void setStDataAdmissao(LocalDate stDataAdmissao) {
        if (stDataAdmissao == null  ) {
            DomainException.with("Data de admissão não pode ser nula");
        }

        this.stDataAdmissao = stDataAdmissao;
    }

    private void setStDataDemissao(LocalDate stDataDemissao) {
        if (stDataDemissao != null && stDataDemissao.isBefore(stDataAdmissao) ) {
            DomainException.with("Data de demissão não pode ser anterior a data de admissão");
        }
        this.stDataDemissao = stDataDemissao;
    }

    public  ServidorTemporario updateDataDemissaoEadmissao(LocalDate stDataDemissao,LocalDate stDataAdmissao){

        if(stDataDemissao != null && stDataAdmissao.isAfter(stDataDemissao)){
            DomainException.with("Data de admissão não pode ser posterior a data de demissão");
        }

        setStDataAdmissao(stDataAdmissao);
        setStDataDemissao(stDataDemissao);

        return this;
    }


}
