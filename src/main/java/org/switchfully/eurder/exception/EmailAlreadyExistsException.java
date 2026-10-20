package org.switchfully.eurder.exception;

public class EmailAlreadyExistsException extends IllegalArgumentException {
    public EmailAlreadyExistsException(String email) {
        super("Email " + email + " is already used");
    }
}
