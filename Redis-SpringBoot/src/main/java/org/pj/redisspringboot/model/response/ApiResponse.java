package org.pj.redisspringboot.model.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ApiResponse<T> {

    private String message;
    private T data;
    private Timestamp responseTime;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
        this.responseTime = new Timestamp(System.currentTimeMillis());
    }
}
