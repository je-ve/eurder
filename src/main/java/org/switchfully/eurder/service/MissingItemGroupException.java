package org.switchfully.eurder.service;

public class MissingItemGroupException extends IllegalArgumentException {
    public MissingItemGroupException() {
        super("An order should have at least 1 item");
    }
}
