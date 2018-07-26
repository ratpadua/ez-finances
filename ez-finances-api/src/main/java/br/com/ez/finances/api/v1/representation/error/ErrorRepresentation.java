package br.com.ez.finances.api.v1.representation.error;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Rest response error representation. Any null values are not included in the JSON.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorRepresentation {

    private String code;

    private String message;

    private ErrorRepresentation(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorRepresentation of(String code, String message) {
        return new ErrorRepresentation(code, message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
