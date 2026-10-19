package org.switchfully.eurder.domain;

import java.time.LocalDate;

public class ItemGroup {
    private final String itemId;
    private final String name;
    private final int amount;
    private final double pricePerUnit;
    private final LocalDate shippingDate;

    public ItemGroup(Item item, int amount) {
        this.itemId = item.getId();
        this.name = item.getName();
        this.amount = amount;
        this.pricePerUnit = item.getPrice();
        if (item.getStock() >= amount) {
            this.shippingDate = LocalDate.now().plusDays(1);
        } else {
            this.shippingDate = LocalDate.now().plusDays(7);
        }
    }

    public double calculateGroupPrice() {
        return this.pricePerUnit * this.amount;
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
