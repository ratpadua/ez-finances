package br.com.ez.finances.web.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.ez.finances.api.v1.representation.error.ErrorRepresentation;
import br.com.ez.finances.domain.error.ErrorCode;
import br.com.ez.finances.web.config.ResourceBundle;

/**
 * Exception handler.
 */
@ControllerAdvice
@ResponseBody
public class EZExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorRepresentation exceptions(Exception ex) {
        return ErrorRepresentation.of(ErrorCode.ERR_001.getCode(),
                ResourceBundle.getMessage(ErrorCode.ERR_001.getKey()));
    }
}
