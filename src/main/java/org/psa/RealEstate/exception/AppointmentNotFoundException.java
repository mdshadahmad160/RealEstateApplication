package org.psa.RealEstate.exception;

public class AppointmentNotFoundException extends RuntimeException{

    public AppointmentNotFoundException(String message) {
        super(message);

    }

    public AppointmentNotFoundException() {
        super();

    }
}
