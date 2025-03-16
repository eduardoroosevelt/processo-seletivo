package br.com.eduardosilva.domain.cidade;

import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.exceptions.DomainException;
import java.util.Objects;

public class CidadeId implements Identifier<Long> {
    private final Long value;

    private CidadeId() {
        this.value = null;
    }

    public CidadeId(final Long value) {
        if (value == null) {
            throw DomainException.with("'CidadeId' should not be null");
        }
        this.value = value;
    }

    public static CidadeId empty() {
        return new CidadeId();
    }

    @Override
    public Long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CidadeId cidadeId = (CidadeId) o;
        return Objects.equals(value, cidadeId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "CidadeId{" +
                "value=" + value +
                '}';
    }
}
