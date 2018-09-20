package br.com.ez.finances.infrastructure.exception;

import br.com.ez.finances.domain.error.ErrorCode;

/**
 * Invalid pofile exception for runtime exceptions.
 */
public class InvalidProfileException extends RuntimeException {

    private ErrorCode errorCode;

    public InvalidProfileException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
