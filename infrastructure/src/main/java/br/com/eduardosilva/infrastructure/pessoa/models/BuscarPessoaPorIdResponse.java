package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.pessoa.PessoaId;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivo;
import br.com.eduardosilva.domain.pessoa.ServidorTemporario;
import br.com.eduardosilva.infrastructure.endereco.models.EnderecoResponse;

import java.time.LocalDate;
import java.util.List;
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
        List<EnderecoResponse> enderecos,
        Set<String> fotos
) {
}
