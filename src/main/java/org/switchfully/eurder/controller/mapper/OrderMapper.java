package org.switchfully.eurder.controller.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.controller.dto.ItemGroupDto;
import org.switchfully.eurder.controller.dto.OrderDto;
import org.switchfully.eurder.domain.Order;

@Component
public class OrderMapper {

    private ItemGroupMapper itemGroupMapper;

    public OrderMapper(ItemGroupMapper itemGroupMapper) {
        this.itemGroupMapper = itemGroupMapper;
    }

    public OrderDto mapToDto(Order savedOrder) {
        return new OrderDto(savedOrder.getId(), savedOrder.getCustomerId(),
                itemGroupMapper.mapToDto(savedOrder.getItemGroups()), savedOrder.getTotalPrice());
    }

}
