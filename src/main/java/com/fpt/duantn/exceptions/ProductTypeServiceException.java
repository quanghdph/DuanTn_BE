package com.fpt.duantn.exceptions;

public class ProductTypeServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public ProductTypeServiceException(String message)
    {
        super(message);
    }
}
