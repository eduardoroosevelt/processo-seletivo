package br.com.eduardosilva.domain.endereco;

import br.com.eduardosilva.domain.Pagination;

import java.util.Optional;

public interface EnderecoGateway {
    EnderecoID nextId();
    Endereco save(Endereco Endereco);
    Optional<Endereco> enderecoOfId(final EnderecoID anId);
    Pagination<EnderecoPreview> findAll(EnderecoSearchQuery search);
}
