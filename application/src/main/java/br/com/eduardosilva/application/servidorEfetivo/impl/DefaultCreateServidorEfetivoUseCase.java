package br.com.eduardosilva.application.servidorEfetivo.impl;

import br.com.eduardosilva.application.endereco.CreateEnderecoUseCase;
import br.com.eduardosilva.application.endereco.impl.DefaultCreateEnderecoUseCase;
import br.com.eduardosilva.application.servidorEfetivo.CreateServidorEfetivoUseCase;
import br.com.eduardosilva.domain.endereco.EnderecoID;
import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.pessoa.Pessoa;
import br.com.eduardosilva.domain.pessoa.PessoaId;
import br.com.eduardosilva.domain.pessoa.ServidorEfetivo;
import br.com.eduardosilva.domain.pessoa.PessoaGateway;

import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultCreateServidorEfetivoUseCase extends CreateServidorEfetivoUseCase {
    private final PessoaGateway servidorEfetivoGateway;

    public DefaultCreateServidorEfetivoUseCase(PessoaGateway servidorEfetivoGateway) {
        this.servidorEfetivoGateway = servidorEfetivoGateway;
    }

    @Override
    public Output execute(Input input) {
        final Optional<Pessoa> opPessoa=servidorEfetivoGateway.existePessoa(
                input.pesNome(),
                input.pesPai(),
                input.pesMae(),
                input.pesDataNascimento()
        );

        Pessoa pessoa;
        if(opPessoa.isPresent()){
            if(opPessoa.get().getServidorEfetivo() != null){
                throw DomainException.with("Pessoa já cadastrada como servidor efetivo ");
            }
            pessoa = opPessoa.get();
        }else{
             pessoa = new Pessoa(
                    servidorEfetivoGateway.nextId(),
                    input.pesNome(),
                    input.pesDataNascimento(),
                    input.pesSexo(),
                    input.pesMae(),
                    input.pesPai(),
                    input.enderecos().stream().map(EnderecoID::new).collect(Collectors.toSet())
            );
        }

        ServidorEfetivo servidorEfetivo = new ServidorEfetivo(input.matricula());
        pessoa.updateServidorEfetivo(servidorEfetivo);

        Pessoa pessoaBD = servidorEfetivoGateway.save(pessoa);

        return new DefaultCreateServidorEfetivoUseCase.StdOutput(pessoaBD.id());
    }

    record StdOutput(PessoaId pesId) implements CreateServidorEfetivoUseCase.Output {}
}
