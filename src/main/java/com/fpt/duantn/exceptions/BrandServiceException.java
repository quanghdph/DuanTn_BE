package com.fpt.duantn.exceptions;

public class BrandServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public BrandServiceException(String message)
    {
        super(message);
    }
}
