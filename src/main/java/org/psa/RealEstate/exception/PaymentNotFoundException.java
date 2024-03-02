package org.psa.RealEstate.exception;

public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException(String message) {
        super(message);

    }

    public PaymentNotFoundException() {
        super();

    }
}
