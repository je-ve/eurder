package org.switchfully.eurder.exception;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException(String memberId) {
        super("No customer was found for id " + memberId);
    }
}
