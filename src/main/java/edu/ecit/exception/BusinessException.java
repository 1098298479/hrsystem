package edu.ecit.exception;

public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 4974085785488984511L;
    private final String errorCode;
    public BusinessException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
