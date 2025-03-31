package br.com.eduardosilva.domain.pessoa;

import br.com.eduardosilva.domain.Entity;
import br.com.eduardosilva.domain.endereco.Endereco;
import br.com.eduardosilva.domain.endereco.EnderecoID;

import java.time.LocalDate;
import java.util.*;

public class Pessoa extends Entity<PessoaId> {;

    private String pesNome;

    private LocalDate pesDataNascimento;

    private String pesSexo;

    private String pesMae;

    private String pesPai;

    private List<Endereco> enderecos;

    private ServidorTemporario servidorTemporario;

    private ServidorEfetivo servidorEfetivo;

    private Set<PessoaFoto> fotos;


    public Pessoa(PessoaId pessoaId,
                  String pesNome,
                  LocalDate pesDataNascimento,
                  String pesSexo,
                  String pesMae,
                  String pesPai,
                  List<Endereco> enderecos,
                  Set<PessoaFoto> fotos) {

        super(pessoaId);
        setPesNome(pesNome);
        setPesDataNascimento(pesDataNascimento);
        setPesSexo(pesSexo);
        setPesMae(pesMae);
        setPesPai(pesPai);
        setEnderecos(enderecos);
        setFotos(fotos);
    }

    public String getPesNome() {
        return pesNome;
    }

    public LocalDate getPesDataNascimento() {
        return pesDataNascimento;
    }

    public String getPesSexo() {
        return pesSexo;
    }

    public String getPesMae() {
        return pesMae;
    }

    public String getPesPai() {
        return pesPai;
    }

    public List<Endereco> getEnderecos() {
        return enderecos != null ? Collections.unmodifiableList(enderecos) : Collections.emptyList();
    }

    public ServidorEfetivo getServidorEfetivo() {
        return servidorEfetivo;
    }

    public Pessoa updatePesNome(String pesNome) {
        if (pesNome != null) {
            setPesNome(pesNome);
        }
        return this;
    }

    public Pessoa updatePesDataNascimento(LocalDate pesDataNascimento) {
        if (pesDataNascimento != null) {
            setPesDataNascimento(pesDataNascimento);
        }
        return this;
    }

    public Pessoa updatePesSexo(String pesSexo) {
        if (pesSexo != null) {
            setPesSexo(pesSexo);
        }
        return this;
    }

    public Pessoa updatePesMae(String pesMae) {
        if (pesMae != null) {
            setPesMae(pesMae);
        }
        return this;
    }

    public Pessoa updatePesPai(String pesPai) {
        if (pesPai != null) {
            setPesPai(pesPai);
        }
        return this;
    }

    public Pessoa updateFotos(Set<PessoaFoto> listPessoaFoto) {
        setFotos(listPessoaFoto);
        return this;
    }

    public Pessoa updateServidorEfetivo(ServidorEfetivo servidorEfetivo){
        this.servidorEfetivo = servidorEfetivo;
        return this;
    }

    public Pessoa updateServidorTemporario(ServidorTemporario servidorTemp){
        this.setServidorTemporario(servidorTemp);
        return this;
    }

    private void setPesNome(String pesNome) {
        if (pesNome != null && !pesNome.isEmpty() && pesNome.length() <= 200) {
            this.pesNome = pesNome;
        } else {
            throw new IllegalArgumentException("Nome não pode ser nulo, vazio ou ter mais de 200 caracteres");
        }
    }

    private void setPesDataNascimento(LocalDate pesDataNascimento) {
        if (pesDataNascimento != null) {
            this.pesDataNascimento = pesDataNascimento;
        } else {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula");
        }
    }

    private void setPesSexo(String pesSexo) {
        if (pesSexo != null && !pesSexo.isEmpty() && pesSexo.length() <= 9) {
            this.pesSexo = pesSexo;
        } else {
            throw new IllegalArgumentException("Sexo não pode ser nulo, vazio ou ter mais de 9 caracteres");
        }
    }

    private void setPesMae(String pesMae) {
        if (pesMae != null && !pesMae.isEmpty() && pesMae.length() <= 200) {
            this.pesMae = pesMae;
        } else {
            throw new IllegalArgumentException("Nome da mãe não pode ser nulo, vazio ou ter mais de 200 caracteres");
        }
    }

    private void setPesPai(String pesPai) {
        if (pesPai != null && !pesPai.isEmpty() && pesPai.length() <= 200) {
            this.pesPai = pesPai;
        } else {
            throw new IllegalArgumentException("Nome do pai não pode ser nulo, vazio ou ter mais de 200 caracteres");
        }
    }

    private void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos != null ? new ArrayList<>(enderecos) : new ArrayList<>();
    }

    public ServidorTemporario getServidorTemporario() {
        return servidorTemporario;
    }

    private void setServidorEfetivo(ServidorEfetivo servidorEfetivo) {
        this.servidorEfetivo = servidorEfetivo;
    }

    private void setServidorTemporario(ServidorTemporario servidorTemporario) {
        this.servidorTemporario = servidorTemporario;
    }

    public Set<PessoaFoto> getFotos() {
        return fotos ;
    }

    private void setFotos(Set<PessoaFoto> fotos) {
        this.fotos = fotos != null ? new HashSet<>(fotos) : Collections.emptySet();
    }

    public void append(PessoaFoto foto) {
        if(fotos == null){
            fotos = new HashSet<>();
        }
        fotos.add(foto);
    }

    public Pessoa updateEnderecos(List<Endereco> endereco) {
        setEnderecos(endereco);
        return this;
    }
}
