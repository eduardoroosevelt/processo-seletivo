package br.com.eduardosilva.infrastructure.unidade.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEnderecoJpaEntity, Long> {
    // Você pode adicionar consultas customizadas aqui se necessário
}