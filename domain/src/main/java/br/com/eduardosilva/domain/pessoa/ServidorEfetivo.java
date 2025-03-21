package br.com.eduardosilva.domain.pessoa;

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
            throw new IllegalArgumentException("matrícula não pode ser nula");
        }

        if (matricula.length() > 200) {
            throw new IllegalArgumentException("matrícula não pode ter mais de 20 caracteres");
        }

        this.matricula = matricula;
    }
}
