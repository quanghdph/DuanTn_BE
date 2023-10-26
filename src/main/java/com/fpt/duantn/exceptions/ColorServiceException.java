package com.fpt.duantn.exceptions;

public class ColorServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public ColorServiceException(String message)
    {
        super(message);
    }
}
