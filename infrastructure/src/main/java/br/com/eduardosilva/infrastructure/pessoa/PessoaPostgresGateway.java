package br.com.eduardosilva.infrastructure.pessoa;

import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import br.com.eduardosilva.domain.pessoa.PessoaId;
import br.com.eduardosilva.domain.pessoa.ServidorTemporario;
import br.com.eduardosilva.infrastructure.endereco.persistence.EnderecoJpaEntity;
import br.com.eduardosilva.infrastructure.mapper.*;
import br.com.eduardosilva.infrastructure.pessoa.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        final PessoaJpaEntity pessoaJpaEntity= PessoaMapper.INSTANCE.pessoaToPessoaJpaEntity(pessoa);

        if (pessoa.getServidorEfetivo() != null) {
            ServidorEfetivoJpaEntity servidorEfetivoJpaEntity = ServidorEfetivoMapper.INSTANCE.servidorEfetivoToServidorEfetivoJpaEntity(pessoa.getServidorEfetivo());
            servidorEfetivoJpaEntity.setPessoa(pessoaJpaEntity);
            pessoaJpaEntity.setServidorEfetivoJpaEntity(servidorEfetivoJpaEntity);
        }

        if (pessoa.getServidorTemporario() != null) {
            ServidorTemporarioJpaEntity servidorTempJpaEntity = ServidorTemporarioMapper.INSTANCE.servidorTemporarioToServidorTemporarioJpaEntity(pessoa.getServidorTemporario());
            servidorTempJpaEntity.setPessoa(pessoaJpaEntity);
            pessoaJpaEntity.setServidorTemporarioJpaEntity(servidorTempJpaEntity);
        }

        if(pessoa.getFotos()!= null){
           Set<PessoaFotoJpaEntity> listPf = pessoa.getFotos().stream().map(f ->{
                PessoaFotoJpaEntity pf = new PessoaFotoJpaEntity();
                pf.setFpData(f.getFpData());
                pf.setFpBucket(f.getFpBucket());
                pf.setFpHash(f.getFpHash());
                pf.setPessoa(pessoaJpaEntity);
                return pf;
            }).collect(Collectors.toSet());

            pessoaJpaEntity.setFotos(listPf);
        }

        PessoaJpaEntity pessoaJpaEntitySave = this.pessoaRepository.save(pessoaJpaEntity);
        return PessoaMapper.INSTANCE.pessoaJpaEntityToPessoa(pessoaJpaEntitySave);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pessoa> pessoaOfId(PessoaId anId) {
        final var t = this.pessoaRepository.findById(anId.value());
        return this.pessoaRepository.findById(anId.value())
                .map(PessoaMapper.INSTANCE::pessoaJpaEntityToPessoa);
    }

}
