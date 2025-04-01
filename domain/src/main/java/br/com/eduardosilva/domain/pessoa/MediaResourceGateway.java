package br.com.eduardosilva.domain.pessoa;

import br.com.eduardosilva.domain.shared.Resource;

public interface MediaResourceGateway {
    PessoaFoto storeImage(PessoaId anId, Resource aResource);
    String generateTemporaryLink(String id);

    void clearResources(PessoaId pessoaId);
}
