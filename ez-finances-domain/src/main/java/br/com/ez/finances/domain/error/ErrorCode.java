package br.com.ez.finances.domain.error;

import org.springframework.util.Assert;

/**
 * Business error codes.
 */
public class ErrorCode {

    private final String code;

    private final String key;

    private ErrorCode(String code, String key) {
        Assert.notNull(key, "code can't be null");
        Assert.hasText(key, "key can't be empty");
        this.code = code;
        this.key = key;
    }

    public String getCode() {
        return this.code;
    }

    public String getKey() {
        return this.key;
    }

    //System Errors - ERR_001 to ERR_099
    public static final ErrorCode ERR_001 = new ErrorCode("ERR_001", "internal.server.error");

    //Profile Errors - ERR_600 to ERR_699

    //Translation Errors - ERR_700 to ERR_799

    //Type Errors - ERR_800 to ERR_899

    //Type Errors - ERR_900 to ERR_999
}
