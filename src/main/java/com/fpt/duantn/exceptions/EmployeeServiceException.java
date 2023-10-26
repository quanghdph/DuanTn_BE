package com.fpt.duantn.exceptions;

public class EmployeeServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public EmployeeServiceException(String message)
    {
        super(message);
    }
}
