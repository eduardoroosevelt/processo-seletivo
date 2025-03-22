package br.com.eduardosilva.domain.lotacao;

import br.com.eduardosilva.domain.Entity;
import br.com.eduardosilva.domain.Identifier;
import br.com.eduardosilva.domain.cidade.CidadeId;
import br.com.eduardosilva.domain.exceptions.DomainException;

import java.util.Objects;

public class LotacaoId  implements Identifier<Long>  {
    private final Long value;

    private LotacaoId() {
        this.value = null;
    }

    public LotacaoId(final Long value) {
        if (value == null) {
            throw DomainException.with("'LotacaoId' should not be null");
        }
        this.value = value;
    }

    public static LotacaoId empty() {
        return new LotacaoId();
    }

    @Override
    public Long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LotacaoId lotacaoId = (LotacaoId) o;
        return Objects.equals(value, lotacaoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "LotacaoId{" +
                "value=" + value +
                '}';
    }
}
