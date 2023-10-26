package com.fpt.duantn.exceptions;

public class ProductDetailServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public ProductDetailServiceException(String message)
    {
        super(message);
    }
}
