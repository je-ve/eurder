package org.switchfully.eurder.api.dto;

public class CreateItemGroupRequest {
    private String itemId;
    private int amount;

    public CreateItemGroupRequest() {
    }

    public CreateItemGroupRequest(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }
    public int getAmount() {
        return amount;
    }
}

