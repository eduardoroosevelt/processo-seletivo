package br.com.eduardosilva.domain.endereco;

import br.com.eduardosilva.domain.Pagination;
import br.com.eduardosilva.domain.pessoa.PessoaId;

import java.util.List;
import java.util.Optional;

public interface EnderecoGateway {
    EnderecoID nextId();
    Endereco save(Endereco endereco);
    void delete(EnderecoID endId);
    Optional<Endereco> enderecoOfId(final EnderecoID anId);
    Pagination<EnderecoPreview> findAll(EnderecoSearchQuery search);
    List<EnderecoID> existsByIds(Iterable<EnderecoID> ids);
}
