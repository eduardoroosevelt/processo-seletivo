package br.com.eduardosilva.infrastructure.cidade.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<CidadeJpaEntity,Long> {
}
