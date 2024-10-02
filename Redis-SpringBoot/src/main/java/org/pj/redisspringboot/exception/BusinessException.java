package org.pj.redisspringboot.exception;

import lombok.Getter;
import org.pj.redisspringboot.constant.EnumErrorCode;

import java.sql.Timestamp;

@Getter
public class BusinessException extends RuntimeException {

    private final int code;
    private final Timestamp responseTime;

    public BusinessException(EnumErrorCode enumErrorCode) {
        super(enumErrorCode.getMessage());
        this.code = enumErrorCode.getCode();
        this.responseTime = new Timestamp(System.currentTimeMillis());
    }

}
