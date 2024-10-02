package org.pj.redisspringboot.constant;

import lombok.Getter;

@Getter
public enum EnumErrorCode {
    USER_NOT_FOUND(404, "User not found"),
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int code;
    private final String message;

    EnumErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
