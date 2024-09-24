package org.alten.business.exception;

public class ResourceNotUpdatedException extends RuntimeException {

    public ResourceNotUpdatedException (String msg) {
        super(msg);
    }

    public ResourceNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotUpdatedException(Throwable cause) {
        super(cause);
    }

}
