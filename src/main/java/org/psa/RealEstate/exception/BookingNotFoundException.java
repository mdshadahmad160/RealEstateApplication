package org.psa.RealEstate.exception;

public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(String message) {
        super(message);

    }

    public BookingNotFoundException() {
        super();

    }
}
