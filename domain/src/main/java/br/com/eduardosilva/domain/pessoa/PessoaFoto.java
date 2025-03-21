package br.com.eduardosilva.domain.pessoa;

import java.time.LocalDate;
import java.util.Objects;

public class PessoaFoto {
    private Long fpId;
    private LocalDate fpData;
    private String fpBucket;
    private String fpHash;

    public PessoaFoto(Long fpId, LocalDate fpData, String fpBucket, String fpHash) {
        this.fpId = fpId;
        this.fpData = fpData;
        this.fpBucket = fpBucket;
        this.fpHash = fpHash;
    }

    public Long getFpId() {
        return fpId;
    }

    public LocalDate getFpData() {
        return fpData;
    }

    public String getFpBucket() {
        return fpBucket;
    }

    public String getFpHash() {
        return fpHash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PessoaFoto that = (PessoaFoto) o;
        return Objects.equals(fpHash, that.fpHash);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fpId);
    }
}
