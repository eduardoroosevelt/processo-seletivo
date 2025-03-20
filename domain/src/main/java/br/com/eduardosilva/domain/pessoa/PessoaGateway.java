package br.com.eduardosilva.domain.pessoa;

import java.time.LocalDate;
import java.util.Optional;

public interface PessoaGateway {
    PessoaId nextId();
    Optional<Pessoa> existePessoa(String nome, String paiNome, String maeNome, LocalDate dtNascimento);
    Pessoa save(Pessoa pessoa);
}
