package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.infrastructure.pessoa.persistence.PessoaJpaEntity;

public record ServidorEfetivoPorUnidadeIdCustom(
        PessoaJpaEntity pessoa,
        String unidadeLotacao
) {
}
