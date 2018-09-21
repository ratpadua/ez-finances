package br.com.ez.finances.infrastructure.exception;

import br.com.ez.finances.domain.error.ErrorCode;

/**
 * File read exception for runtime exceptions.
 */
public class FileReadException extends RuntimeException {

    private ErrorCode errorCode;

    public FileReadException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
