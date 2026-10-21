package org.switchfully.eurder.api.dto;

import java.util.List;

public class OrderDto {
    private final String id;
    private final String customerId; // Tracks the known customer by ID
    private final List<ItemGroupDto> itemGroups;
    private final double totalPrice;

    public OrderDto(String orderId, String customerId, List<ItemGroupDto> itemGroupDtos, double totalPrice) {
        this.id = orderId;
        this.customerId = customerId;
        this.itemGroups = itemGroupDtos;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ItemGroupDto> getItemGroups() {
        return itemGroups;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
