package com.fpt.duantn.exceptions;

public class PromotionServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public PromotionServiceException(String message)
    {
        super(message);
    }
}
