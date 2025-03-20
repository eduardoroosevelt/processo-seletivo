package br.com.eduardosilva.domain.pessoa;

import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.exceptions.DomainException;

import java.util.Objects;

public class PessoaId implements Identifier<Long> {
    private final Long value;

    private PessoaId() {
        this.value = null;
    }

    public PessoaId(final Long value) {
        if (value == null) {
            throw DomainException.with("'PessoaId' should not be null");
        }
        this.value = value;
    }

    public static PessoaId empty() {
        return new PessoaId();
    }

    @Override
    public Long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PessoaId pessoaId = (PessoaId) o;
        return Objects.equals(value, pessoaId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "pessoaId{" +
                "value=" + value +
                '}';
    }
}
