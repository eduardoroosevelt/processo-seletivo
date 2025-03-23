package br.com.eduardosilva.infrastructure.config;

import br.com.eduardosilva.domain.exceptions.DomainException;
import br.com.eduardosilva.domain.exceptions.ExceptionMensagem;
import br.com.eduardosilva.domain.exceptions.NotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ DomainException.class, Exception.class })
    protected ResponseEntity<Object> handleExceptionDomain(RuntimeException ex, WebRequest request) {
        // Carrega os dados do erro
        String mensagemUsuario = ex.getMessage();

        String mensagemDesenvolvedor = tratarMsgDesenvolvedor(ex);

        // Cria a lista de erros
        List<DetalhesErro> erros = Arrays.asList(DetalhesErro.builder().addMsgUsuario(mensagemUsuario)
                .addMsgDesenvolvedor(mensagemDesenvolvedor).addStatus(HttpStatus.BAD_REQUEST)
                .addHttpMethod(getHttpMethod(request)).addPath(getPath(request)).build());
        // Retorna o erro tratado a aplicação cliente

        return super.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ NotFoundException.class })
    protected ResponseEntity<Object> handleExceptionNotFound(RuntimeException ex, WebRequest request) {
        // Carrega os dados do erro
        String mensagemUsuario = ex.getMessage();

        String mensagemDesenvolvedor = tratarMsgDesenvolvedor(ex);

        // Cria a lista de erros
        List<DetalhesErro> erros = Arrays.asList(DetalhesErro.builder().addMsgUsuario(mensagemUsuario)
                .addMsgDesenvolvedor(mensagemDesenvolvedor).addStatus(HttpStatus.BAD_REQUEST)
                .addHttpMethod(getHttpMethod(request)).addPath(getPath(request)).build());
        // Retorna o erro tratado a aplicação cliente

        return super.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class, NoSuchElementException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(RuntimeException ex, WebRequest request) {

        // Carrega os dados do erro
        String mensagemUsuario = ExceptionMensagem.RECURSO_NAO_ENCONTRADO;

        String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        // Cria a lista de erros
        List<DetalhesErro> erros = Arrays.asList(DetalhesErro.builder().addMsgUsuario(mensagemUsuario)
                .addMsgDesenvolvedor(mensagemDesenvolvedor).addStatus(HttpStatus.NOT_FOUND)
                .addHttpMethod(getHttpMethod(request)).addPath(getPath(request)).build());
        // Retorna o erro tratado ao cliente
        return super.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ NoSuchMessageException.class })
    public ResponseEntity<Object> handleEmptyResultSuchMessageException(NoSuchMessageException ex, WebRequest request) {

        // Carrega os dados do erro
        String mensagemUsuario = ExceptionMensagem.ARQUIVO_MENSAGEM_NAO_ENCONTRADO;

        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

        // Cria a lista de erros
        List<DetalhesErro> erros = Arrays.asList(DetalhesErro.builder().addMsgUsuario(mensagemUsuario)
                .addMsgDesenvolvedor(mensagemDesenvolvedor).addStatus(HttpStatus.NOT_FOUND)
                .addHttpMethod(getHttpMethod(request)).addPath(getPath(request)).build());

        // Retorna o erro tratado ao cliente
        return super.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                        WebRequest request) {

        String mensagemUsuario = ExceptionMensagem.RECURSO_VIOLACAO_CHAVE;

        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

        List<DetalhesErro> erros = Arrays.asList(DetalhesErro.builder().addMsgUsuario(mensagemUsuario)
                .addMsgDesenvolvedor(mensagemDesenvolvedor).addStatus(HttpStatus.BAD_REQUEST)
                .addHttpMethod(getHttpMethod(request)).addPath(getPath(request)).build());
        return super.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private String getPath(WebRequest request) {

        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private String getHttpMethod(WebRequest request) {

        return ((ServletWebRequest) request).getRequest().getMethod();
    }

    private String tratarMsgDesenvolvedor(RuntimeException ex) {
        // Carrega a mensagem principal
        String msg = "Causa: " + (ex.getCause() != null ? ex.getCause().toString() : ex.toString());
        // existe pilha de erro
        if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
            // Percorre a lista de erro
            for (int i = 0; i < ex.getStackTrace().length; i++) {
                msg += "\n" + ex.getStackTrace()[i].toString();
            }
        }
        // Retorna a mensagem de erro do desenvolvedor
        return msg;
    }

    private List<DetalhesErro> criarListaDeErros(BindingResult bindingResult, WebRequest request) {
        List<DetalhesErro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {

            String mensagemUsuario = fieldError.getDefaultMessage();
            String mensagemDesenvolvedor = fieldError.toString();
            erros.add(DetalhesErro.builder().addMsgUsuario(mensagemUsuario).addMsgDesenvolvedor(mensagemDesenvolvedor)
                    .addStatus(HttpStatus.BAD_REQUEST).addHttpMethod(getHttpMethod(request)).addPath(getPath(request))
                    .build());
        }

        return erros;
    }

}
