package org.switchfully.eurder.exception;

public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException(String itemId) {
        super("No item was found for id " + itemId);
    }
}