package br.com.eduardosilva.application;

public record CreateEnderecoCommand(String endTipoLogradouro,
                                    String endLogradouro,
                                    Integer endNumero,
                                    String endBairro,
                                    Long cidadeId) {
}
