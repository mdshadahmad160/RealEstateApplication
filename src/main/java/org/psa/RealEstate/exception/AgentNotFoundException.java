package org.psa.RealEstate.exception;

public class AgentNotFoundException extends RuntimeException{


    public AgentNotFoundException(String message) {
        super(message);

    }

    public AgentNotFoundException () {
        super();

    }
}
