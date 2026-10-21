package org.switchfully.eurder.exception;

public class ItemNotFoundException extends NotFoundException {
    public ItemNotFoundException(String itemId) {
        super("No item was found for id " + itemId);
    }
}
