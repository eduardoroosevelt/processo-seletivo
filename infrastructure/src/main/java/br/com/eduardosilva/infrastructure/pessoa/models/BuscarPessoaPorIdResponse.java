package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.pessoa.PessoaId;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivo;
import br.com.eduardosilva.domain.pessoa.ServidorTemporario;

import java.time.LocalDate;
import java.util.Set;

public record BuscarPessoaPorIdResponse(
        Long pesId,
        String pesNome,
        LocalDate pesDataNascimento,
        String pesSexo,
        String pesMae,
        String pesPai,
        ServidorTemporario servidorTemp,
        ServidorEfetivo servidorEfetivo,
        Set<Long> enderecos,
        Set<String> fotos
) {
}
