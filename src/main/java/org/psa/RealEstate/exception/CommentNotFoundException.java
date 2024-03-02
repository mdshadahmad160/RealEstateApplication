package org.psa.RealEstate.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String message) {
        super(message);

    }

    public CommentNotFoundException() {
        super();

    }
}
