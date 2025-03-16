package br.com.eduardosilva.infrastructure.unidade.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeRepository extends JpaRepository<UnidadeJpaEntity,Long> {
}
