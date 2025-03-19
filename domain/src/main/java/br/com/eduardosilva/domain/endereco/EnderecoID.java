package br.com.eduardosilva.domain.endereco;

import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.exceptions.DomainException;

import java.util.Objects;

public class EnderecoID implements Identifier<Long> {
    private final Long value;

    private EnderecoID() {
        this.value = null;
    }

    public EnderecoID(final Long value) {
        if (value == null) {
            throw DomainException.with("'EnderecoID' should not be null");
        }
        this.value = value;
    }

    public static EnderecoID empty() {
        return new EnderecoID();
    }

    @Override
    public Long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EnderecoID enderecoId = (EnderecoID) o;
        return Objects.equals(value, enderecoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "EnderecoId{" +
                "value=" + value +
                '}';
    }
}
