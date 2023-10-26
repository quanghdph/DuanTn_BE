package com.fpt.duantn.exceptions;

public class MaterialServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public MaterialServiceException(String message)
    {
        super(message);
    }
}
