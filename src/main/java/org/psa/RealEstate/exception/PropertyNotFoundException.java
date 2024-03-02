package org.psa.RealEstate.exception;

public class PropertyNotFoundException extends RuntimeException{
    public PropertyNotFoundException(String message) {
        super(message);

    }

    public PropertyNotFoundException() {
        super();

    }
}
