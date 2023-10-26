package com.fpt.duantn.exceptions;

public class WaistbandServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public WaistbandServiceException(String message)
    {
        super(message);
    }
}
