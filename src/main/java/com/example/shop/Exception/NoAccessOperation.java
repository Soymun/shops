package com.example.shop.Exception;

public class NoAccessOperation extends RuntimeException{

    public NoAccessOperation(String message) {
        super(message);
    }
}
