package com.fpt.duantn.exceptions;

public class ImageServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public ImageServiceException(String message)
    {
        super(message);
    }
}
