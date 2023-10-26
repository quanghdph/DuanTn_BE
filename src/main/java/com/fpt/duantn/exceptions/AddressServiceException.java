package com.fpt.duantn.exceptions;

public class AddressServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public AddressServiceException(String message)
    {
        super(message);
    }
}
