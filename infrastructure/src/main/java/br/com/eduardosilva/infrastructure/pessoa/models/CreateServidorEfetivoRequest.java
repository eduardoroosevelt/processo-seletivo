package br.com.eduardosilva.infrastructure.pessoa.models;

import br.com.eduardosilva.application.CreateEnderecoCommand;
import br.com.eduardosilva.application.pessoa.servidorEfetivo.CreateServidorEfetivoUseCase;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record CreateServidorEfetivoRequest(
        String pesNome,
        LocalDate pesDataNascimento,
        String pesSexo,
        String pesMae,
        String pesPai,
        List<CreateEnderecoCommand> enderecos,
        String matricula
) implements CreateServidorEfetivoUseCase.Input {


}


