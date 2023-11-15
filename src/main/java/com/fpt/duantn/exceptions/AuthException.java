package com.fpt.duantn.exceptions;

public class AuthException extends RuntimeException{
    private static final long serialVersionUID = 1348771109171435607L;

    public AuthException(String message)
    {
        super(message);
    }
}