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
    public static final ErrorCode ERR_002 = new ErrorCode("ERR_002", "entity.not.found");
    public static final ErrorCode ERR_003 = new ErrorCode("ERR_003", "invalid.format");
    public static final ErrorCode ERR_004 = new ErrorCode("ERR_004", "missing.parameter");

    //Profile Errors - ERR_600 to ERR_699
    public static final ErrorCode ERR_600 = new ErrorCode("ERR_600", "profile.not.found");

    //Source Errors - ERR_700 to ERR_799
    public static final ErrorCode ERR_700 = new ErrorCode("ERR_700", "source.not.found");

    //Translation Errors - ERR_800 to ERR_899
    public static final ErrorCode ERR_800 = new ErrorCode("ERR_800", "translation.not.found");

    //Transaction Errors - ERR_900 to ERR_999
    public static final ErrorCode ERR_900 = new ErrorCode("ERR_900", "transaction.not.found");
}
