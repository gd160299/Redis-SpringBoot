package org.pj.redisspringboot.Enum;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(404, "User not found"),
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
