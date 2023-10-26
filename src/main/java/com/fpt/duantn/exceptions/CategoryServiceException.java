package com.fpt.duantn.exceptions;

public class CategoryServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public CategoryServiceException(String message)
    {
        super(message);
    }
}
