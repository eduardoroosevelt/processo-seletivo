package br.com.eduardosilva.domain.exceptions;

public record Error(String property, String message) {

    public Error(String message) {
        this(message, "");
    }
}
