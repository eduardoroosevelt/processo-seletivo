package br.com.eduardosilva.infrastructure.pessoa.persistence;

import br.com.eduardosilva.domain.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<PessoaJpaEntity,Long> {
    @Query("""
            select p
            from PessoaJpaEntity p
            where
                p.pesNome = :nome and
                p.pesPai = :paiNome and
                p.pesMae = :maeNome and
                p.pesDataNascimento = :dtNascimento
        """)
    Optional<PessoaJpaEntity> existePessoa(String nome, String paiNome, String maeNome, LocalDate dtNascimento);
}
