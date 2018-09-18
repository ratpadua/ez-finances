package br.com.ez.finances.infrastructure.exception;

import br.com.ez.finances.domain.error.ErrorCode;

/**
 * Not found exception for runtime exceptions.
 */
public class NotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public NotFoundException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
