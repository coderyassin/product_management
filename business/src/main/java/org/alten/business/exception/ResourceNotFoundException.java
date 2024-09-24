package org.alten.business.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String msg) {
        super(msg);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

}
