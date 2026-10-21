package org.switchfully.eurder.api.dto;

import java.time.LocalDate;

public class ItemGroupDto {
    private final String itemId;
    private final String name;
    private final int amount;
    private final double pricePerUnit;
    private final LocalDate shippingDate;

    public ItemGroupDto(String itemId, String name, int amount, double pricePerUnit, LocalDate shippingDate) {
        this.itemId = itemId;
        this.name = name;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
        this.shippingDate = shippingDate;
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

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
