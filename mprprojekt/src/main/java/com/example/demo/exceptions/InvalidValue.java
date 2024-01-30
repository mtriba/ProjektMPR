package com.example.demo.exceptions;

public class InvalidValue extends RuntimeException{
    public InvalidValue(String message){
        super(message);
    }
}
