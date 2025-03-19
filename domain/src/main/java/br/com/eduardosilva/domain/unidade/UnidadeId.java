package br.com.eduardosilva.domain.unidade;

import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;

import java.util.Objects;

public class UnidadeId implements Identifier<Long> {
    private final Long value;

    private UnidadeId() {
        this.value = null;
    }

    public UnidadeId(final Long value) {
        if (value == null) {
            throw DomainException.with("'UnidadeId' should not be null");
        }
        this.value = value;
    }

    public static UnidadeId empty() {
        return new UnidadeId();
    }

    @Override
    public Long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnidadeId unidadeId = (UnidadeId) o;
        return Objects.equals(value, unidadeId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "UnidadeId{" +
                "value=" + value +
                '}';
    }
}
