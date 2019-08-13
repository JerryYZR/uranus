package com.team7.uranus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData<T> {
    private int code;
    private String message;
    private T data;

    public ResponseData() {
        this.code = 200;
    }

}
