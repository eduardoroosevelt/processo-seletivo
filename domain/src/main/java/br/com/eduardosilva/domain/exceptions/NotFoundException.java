package br.com.eduardosilva.domain.exceptions;

import java.util.List;

public class NotFoundException extends DomainException{
    protected NotFoundException(String aMessage, List<Error> anErrors) {
        super(aMessage, anErrors);
    }
}
