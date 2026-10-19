package org.switchfully.eurder.controller.dto;

public class UpdateItemDto {
    private final String name;
    private final String description;
    private final double price;
    private final int stock;

    public UpdateItemDto(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
