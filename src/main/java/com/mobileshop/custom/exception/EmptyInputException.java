package com.mobileshop.custom.exception;

import org.springframework.stereotype.Component;

@Component
public class EmptyInputException extends RuntimeException{

    private String errorCode;
    private String errorMessage;
    private static final long serialVersionID = 1L;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public EmptyInputException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public EmptyInputException() {
    }
}
