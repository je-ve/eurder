package org.switchfully.eurder.controller.dto;

import org.switchfully.eurder.domain.ItemGroup;

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
}
