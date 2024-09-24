package org.alten.business.exception;

public class ResourceNotDeletedException extends RuntimeException {

    public ResourceNotDeletedException (String msg) {
        super(msg);
    }

    public ResourceNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotDeletedException(Throwable cause) {
        super(cause);
    }

}
