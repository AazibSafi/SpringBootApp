package com.springboot.app.exception;

public class PersonaException extends Exception {

    private static final long serialVersionUID = -1245878720609969852L;

    public PersonaException() {
        super();
    }

    public PersonaException(String message) {
        super(message);
    }

    public PersonaException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonaException(Throwable cause) {
        super(cause);
    }

}
