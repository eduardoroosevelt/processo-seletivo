package br.com.eduardosilva.infrastructure.pessoa;

import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import br.com.eduardosilva.domain.pessoa.PessoaId;
import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import br.com.eduardosilva.infrastructure.mapper.EnderecoMapper;
import br.com.eduardosilva.infrastructure.mapper.PessoaMapper;
import br.com.eduardosilva.infrastructure.mapper.ServidorEfetivoMapper;
import br.com.eduardosilva.infrastructure.pessoa.persistence.PessoaJpaEntity;
import br.com.eduardosilva.infrastructure.pessoa.persistence.PessoaRepository;
import br.com.eduardosilva.infrastructure.pessoa.persistence.ServidorEfetivoJpaEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class PessoaPostgresGateway implements PessoaGateway {

    private final PessoaRepository pessoaRepository;

    public PessoaPostgresGateway(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public PessoaId nextId() {
        return PessoaId.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pessoa> existePessoa(String nome, String paiNome, String maeNome, LocalDate dtNascimento) {
        return pessoaRepository
                .existePessoa(nome, paiNome, maeNome, dtNascimento)
                .map(PessoaMapper.INSTANCE::pessoaJpaEntityToPessoa);
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        PessoaJpaEntity pessoaJpaEntity= PessoaMapper.INSTANCE.pessoaToPessoaJpaEntity(pessoa);

        if (pessoa.getServidorEfetivo() != null) {
            ServidorEfetivoJpaEntity servidorEfetivoJpaEntity = ServidorEfetivoMapper.INSTANCE.servidorEfetivoToServidorEfetivoJpaEntity(pessoa.getServidorEfetivo());
            servidorEfetivoJpaEntity.setPessoa(pessoaJpaEntity);  // Associa a Pessoa ao ServidorEfetivo
            pessoaJpaEntity.setServidorEfetivoJpaEntity(servidorEfetivoJpaEntity);
        }

        pessoaJpaEntity = this.pessoaRepository.save(pessoaJpaEntity);
        return PessoaMapper.INSTANCE.pessoaJpaEntityToPessoa(pessoaJpaEntity);
    }
}
