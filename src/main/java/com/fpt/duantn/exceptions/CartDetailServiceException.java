package com.fpt.duantn.exceptions;

public class CartDetailServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public CartDetailServiceException(String message)
    {
        super(message);
    }
}
