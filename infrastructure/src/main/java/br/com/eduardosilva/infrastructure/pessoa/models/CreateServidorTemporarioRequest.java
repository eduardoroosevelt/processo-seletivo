package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.application.CreateEnderecoCommand;
import br.com.eduardosilva.application.pessoa.servidorTemporario.CreateServidorTemporarioUseCase;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record CreateServidorTemporarioRequest(
        String pesNome,
        LocalDate pesDataNascimento,
        String pesSexo,
        String pesMae,
        String pesPai,
        List<CreateEnderecoCommand> enderecos,
        LocalDate stDataDemissao,
        LocalDate stDataAdmissao
) implements CreateServidorTemporarioUseCase.Input {
}
