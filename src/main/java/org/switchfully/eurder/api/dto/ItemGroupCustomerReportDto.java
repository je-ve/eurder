package org.switchfully.eurder.api.dto;

public class ItemGroupCustomerReportDto {
    private final String itemId;
    private final String name;
    private final int amount;
    private final double totalPrice;

    public ItemGroupCustomerReportDto(String itemId, String name, int amount, double pricePerUnit) {
        this.itemId = itemId;
        this.name = name;
        this.amount = amount;
        this.totalPrice = pricePerUnit*amount;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotalPrice() { return totalPrice; }
}


