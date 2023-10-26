package com.fpt.duantn.exceptions;

public class CustomerServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public CustomerServiceException(String message)
    {
        super(message);
    }
}
