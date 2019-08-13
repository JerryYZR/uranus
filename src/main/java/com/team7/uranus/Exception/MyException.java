package com.team7.uranus.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyException extends RuntimeException{

    private int errorCode;

    private String message;

}
