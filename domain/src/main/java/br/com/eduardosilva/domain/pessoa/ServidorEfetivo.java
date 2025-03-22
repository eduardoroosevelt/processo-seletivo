package br.com.eduardosilva.domain.pessoa;

import br.com.eduardosilva.domain.exceptions.DomainException;

public class ServidorEfetivo {
    String matricula;

    public ServidorEfetivo(String matricula) {
        setMatricula(matricula);
    }

    public String getMatricula() {
        return matricula;
    }

    public ServidorEfetivo updateMatricula(String matricula){
        setMatricula(matricula);
        return this;
    }

    private void setMatricula(String matricula) {

        if (matricula == null || matricula.isEmpty() ) {
            DomainException.with("matrícula não pode ser nula");
        }

        if (matricula.length() > 200) {
            DomainException.with("matrícula não pode ter mais de 20 caracteres");
        }

        this.matricula = matricula;
    }
}
