package com.fpt.duantn.exceptions;

public class BillDetailServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public BillDetailServiceException(String message)
    {
        super(message);
    }
}
