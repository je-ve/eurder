package org.switchfully.eurder.api.dto;

import java.math.BigDecimal;

public class ItemGroupCustomerReportDto {
    private final String itemId;
    private final String name;
    private final int amount;
    private final BigDecimal totalPrice;

    public ItemGroupCustomerReportDto(String itemId, String name, int amount, BigDecimal pricePerUnit) {
        this.itemId = itemId;
        this.name = name;
        this.amount = amount;
        this.totalPrice = pricePerUnit.multiply(BigDecimal.valueOf(amount));
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}


