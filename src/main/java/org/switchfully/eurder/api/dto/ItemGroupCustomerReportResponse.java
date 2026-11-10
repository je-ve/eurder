package org.switchfully.eurder.api.dto;

import java.math.BigDecimal;

public class ItemGroupCustomerReportResponse {
    private final String name;
    private final int amount;
    private final BigDecimal totalPrice;

    public ItemGroupCustomerReportResponse(String name, int amount, BigDecimal pricePerUnit) {

        this.name = name;
        this.amount = amount;
        this.totalPrice = pricePerUnit.multiply(BigDecimal.valueOf(amount));
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


