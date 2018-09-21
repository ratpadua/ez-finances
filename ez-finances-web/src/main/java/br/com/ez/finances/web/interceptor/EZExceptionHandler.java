package br.com.ez.finances.web.interceptor;

import java.io.FileNotFoundException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.ez.finances.api.v1.representation.error.ErrorRepresentation;
import br.com.ez.finances.domain.error.ErrorCode;
import br.com.ez.finances.infrastructure.exception.FileReadException;
import br.com.ez.finances.infrastructure.exception.InvalidProfileException;
import br.com.ez.finances.infrastructure.exception.NotFoundException;
import br.com.ez.finances.web.config.ResourceBundle;

/**
 * Exception handler.
 */
@ControllerAdvice
@ResponseBody
public class EZExceptionHandler {

    @ExceptionHandler({FileReadException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorRepresentation fileReadException(FileReadException ex) {
        return ErrorRepresentation.of(ex.getErrorCode().getCode(),
                ResourceBundle.getMessage(ex.getErrorCode().getKey()),null);
    }

    @ExceptionHandler({ServletRequestBindingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorRepresentation servletRequestBindingException(ServletRequestBindingException ex) {
        return ErrorRepresentation.of(ErrorCode.ERR_007.getCode(),
                ResourceBundle.getMessage(ErrorCode.ERR_007.getKey()),
                ex.getMessage());
    }

    @ExceptionHandler({InvalidProfileException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorRepresentation invalidProfileException(InvalidProfileException ex) {
        return ErrorRepresentation.of(ex.getErrorCode().getCode(),
                ResourceBundle.getMessage(ex.getErrorCode().getKey()),null);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorRepresentation methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ErrorRepresentation.of(ErrorCode.ERR_006.getCode(),
                ResourceBundle.getMessage(ErrorCode.ERR_006.getKey()),
                ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorRepresentation methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ErrorRepresentation.of(ErrorCode.ERR_005.getCode(),
                ResourceBundle.getMessage(ErrorCode.ERR_005.getKey()),
                ex.getMessage());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorRepresentation missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ErrorRepresentation.of(ErrorCode.ERR_004.getCode(),
                ResourceBundle.getMessage(ErrorCode.ERR_004.getKey()),
                ex.getMessage());
    }

    @ExceptionHandler({FileNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorRepresentation fileNotFoundException(FileNotFoundException ex) {
        return ErrorRepresentation.of(ErrorCode.ERR_920.getCode(),
                ResourceBundle.getMessage(ErrorCode.ERR_920.getKey()),
                ex.getMessage());
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorRepresentation notFoundException(NotFoundException ex) {
        return ErrorRepresentation.of(ex.getErrorCode().getCode(),
                ResourceBundle.getMessage(ex.getErrorCode().getKey()), null);
    }

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
