package org.pj.redisspringboot.Exception;

import lombok.Getter;
import org.pj.redisspringboot.Enum.ErrorCode;

import java.time.LocalDateTime;

@Getter
public class BusinessException extends RuntimeException {

    private final int code;
    private final LocalDateTime timestamp;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.timestamp = LocalDateTime.now();
    }

}
