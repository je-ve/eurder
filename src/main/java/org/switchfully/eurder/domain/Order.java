package org.switchfully.eurder.domain;

import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final String customerId; // Tracks the known customer by ID
    private final List<ItemGroup> itemGroups;
    private final double totalPrice;

    public Order(String customerId, List<ItemGroup> itemGroups) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.itemGroups = itemGroups;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return itemGroups.stream()
                .mapToDouble(ItemGroup::calculateGroupPrice)
                .sum();
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
