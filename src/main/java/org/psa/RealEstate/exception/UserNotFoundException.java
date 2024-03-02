package org.psa.RealEstate.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);

    }

    public UserNotFoundException() {
        super();

    }

    public UserNotFoundException(String user, String s, int i) {
    }
}
