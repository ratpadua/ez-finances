package br.com.ez.finances.web.interceptor;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.ez.finances.api.v1.representation.error.ErrorRepresentation;
import br.com.ez.finances.domain.error.ErrorCode;
import br.com.ez.finances.web.config.ResourceBundle;

/**
 * Exception handler.
 */
@ControllerAdvice
@ResponseBody
public class EZExceptionHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorRepresentation httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ErrorRepresentation.of(ErrorCode.ERR_003.getCode(),
                ResourceBundle.getMessage(ErrorCode.ERR_003.getKey()),
                ex.getMessage());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorRepresentation entityNotFoundException(EntityNotFoundException ex) {
        return ErrorRepresentation.of(ErrorCode.ERR_002.getCode(),
                ResourceBundle.getMessage(ErrorCode.ERR_002.getKey()),
                ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorRepresentation exception(Exception ex) {
        return ErrorRepresentation.of(ErrorCode.ERR_001.getCode(),
                ResourceBundle.getMessage(ErrorCode.ERR_001.getKey()),
                ex.getMessage());
    }
}
