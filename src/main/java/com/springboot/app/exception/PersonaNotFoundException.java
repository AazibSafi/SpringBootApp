package com.springboot.app.exception;

public class PersonaNotFoundException extends PersonaException {

    private static final long serialVersionUID = 7246329572640158477L;

    public PersonaNotFoundException() {
        super();
    }

    public PersonaNotFoundException(String message) {
        super(message);
    }

    public PersonaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonaNotFoundException(Throwable cause) {
        super(cause);
    }

}
