package com.example.shop.Exception;

public class MyAuthenticationException extends RuntimeException{
    public MyAuthenticationException(String message) {
        super(message);
    }
}
