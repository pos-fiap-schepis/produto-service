package br.com.fiap.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ViolacaoDominioExcecao.class)
    protected ResponseEntity<Object> handleViolacaoDominioExcecao(ViolacaoDominioExcecao ex) {
        return new ResponseEntity(new Mensagem(ex.mensagem), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
