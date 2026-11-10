package org.switchfully.eurder.api.dto;

import java.math.BigDecimal;

public class UpdateItemDto {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int stock;

    public UpdateItemDto(String name, String description, BigDecimal price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
