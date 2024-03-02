package org.psa.RealEstate.exception;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(String message) {
        super(message);

    }

    public ReviewNotFoundException() {
        super();

    }
}
