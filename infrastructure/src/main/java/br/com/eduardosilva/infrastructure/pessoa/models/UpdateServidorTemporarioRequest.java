package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.application.UpdateEnderecoCommand;
import br.com.eduardosilva.application.pessoa.servidorTemporario.UpdateServidorTemporarioUseCase;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UpdateServidorTemporarioRequest(
        Long pesId,
        String pesNome,
        LocalDate pesDataNascimento,
        String pesSexo,
        String pesMae,
        String pesPai,
        List<UpdateEnderecoCommand> enderecos,
        LocalDate stDataDemissao,
        LocalDate stDataAdmissao
) implements UpdateServidorTemporarioUseCase.Input {
}
