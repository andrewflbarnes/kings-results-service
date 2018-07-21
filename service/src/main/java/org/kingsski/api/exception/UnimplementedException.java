package org.kingsski.api.exception;

public class UnimplementedException extends RuntimeException {
    public UnimplementedException() {
        super();
    }

    public UnimplementedException(String msg) {
        super(msg);
    }
}
