package br.com.eduardosilva.infrastructure.unidade;

import br.com.eduardosilva.domain.unidade.UnidadeGateway;
import br.com.eduardosilva.infrastructure.unidade.persistence.UnidadeRepository;
import org.springframework.stereotype.Component;

@Component
public class UnidadePostgreGateway implements UnidadeGateway {

    private final UnidadeRepository unidadeRepository;

    public UnidadePostgreGateway(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }
}
