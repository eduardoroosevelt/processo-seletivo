package br.com.eduardosilva.application.pessoa.servidorEfetivo.impl;

import br.com.eduardosilva.application.pessoa.servidorEfetivo.DeleteServidorEfetivoUseCase;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.exceptions.NotFoundException;
import br.com.eduardosilva.domain.lotacao.Lotacao;
import br.com.eduardosilva.domain.lotacao.LotacaoGateway;
import br.com.eduardosilva.domain.pessoa.MediaResourceGateway;
import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;
import br.com.eduardosilva.domain.pessoa.PessoaId;

public class DefeultDeleteServidorEfetivoUseCase extends DeleteServidorEfetivoUseCase {
    private final PessoaGateway pessoaGateway;
    private final MediaResourceGateway mediaResourceGateway;
    private final LotacaoGateway lotacaoGateway;

    public DefeultDeleteServidorEfetivoUseCase(PessoaGateway pessoaGateway,
                                               MediaResourceGateway mediaResourceGateway,
                                               LotacaoGateway lotacaoGateway) {
        this.pessoaGateway = pessoaGateway;
        this.mediaResourceGateway = mediaResourceGateway;
        this.lotacaoGateway = lotacaoGateway;
    }

    @Override
    public void execute(Long anIn) {
        PessoaId pesId = new PessoaId(anIn);
        Pessoa pessoa = pessoaGateway.pessoaOfId(pesId)
                .orElseThrow(() -> NotFoundException.with("Pessoa com id %s não pode ser encontrado".formatted(anIn)));

        if (lotacaoGateway.existeLotacaoPorPesId(pesId)){
                throw   DomainException.with("Pessoa nao pode ser deletado, pois existe lotação vinculado a ela");
        }

        if(pessoa.getServidorTemporario() == null){
            pessoaGateway.delete(pessoa.id());
            mediaResourceGateway.clearResources(new PessoaId(anIn));
        }else{
            pessoa.updateServidorEfetivo(null);
            pessoaGateway.save(pessoa);
        }

    }
}
