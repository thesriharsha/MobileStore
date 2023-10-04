package com.mobileshop.custom.exception;

public class IdNotFoundException extends Exception {

    private String errorCode;
    private String errorMessage;
    private static final long serialVersionID = 1L;

    public IdNotFoundException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public IdNotFoundException() {
    }

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
}
