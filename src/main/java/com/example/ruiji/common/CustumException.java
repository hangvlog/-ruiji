package com.example.ruiji.common;

/**
 * 自定义业务异常类
 */
public class CustumException extends RuntimeException{
    public CustumException(String message){
        super(message);
    }

}
