package br.com.ez.finances.api.v1.representation.error;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Rest response error representation. Any null values are not included in the JSON.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorRepresentation {

    private String code;

    private String message;

    private String exceptionMessage;

    private ErrorRepresentation(String code, String message, String exceptionMessage) {
        this.code = code;
        this.message = message;
        this.exceptionMessage = exceptionMessage;
    }

    public static ErrorRepresentation of(String code, String message, String exceptionMessage) {
        return new ErrorRepresentation(code, message, exceptionMessage);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
