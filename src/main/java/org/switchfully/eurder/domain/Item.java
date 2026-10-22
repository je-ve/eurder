package org.switchfully.eurder.domain;

import java.util.UUID;

public class Item {

    private final String id;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Item(String name, String description, double price, int stock) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void lowerStock(int quantity) {
        this.stock -= quantity;
    }

    public void addStock(int quantity) {
        this.stock += quantity;
    }


    //SEED
    public static Item createWithId(String id, String name, String description, double price, int stock) {
        return new Item(id, name, description, price, stock);
    }

    private Item(String id, String name, String description, double price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

}
