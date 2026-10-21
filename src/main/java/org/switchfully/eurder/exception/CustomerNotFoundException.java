package org.switchfully.eurder.exception;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException(String customerId) {
        super("No customer was found for id " + customerId);
    }
}
