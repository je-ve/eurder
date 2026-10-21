package org.switchfully.eurder.api.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.api.dto.CustomerReportDto;
import org.switchfully.eurder.api.dto.ItemGroupDto;
import org.switchfully.eurder.api.dto.OrderCustomerReportDto;
import org.switchfully.eurder.api.dto.OrderDto;
import org.switchfully.eurder.domain.ItemGroup;
import org.switchfully.eurder.domain.Order;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

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

    public OrderCustomerReportDto mapToCustomerReportDto(Order order) {
        return new OrderCustomerReportDto(order.getId(),
                itemGroupMapper.mapToCustomerReportDto(order.getItemGroups()), order.getTotalPrice());
    }

    public List<OrderCustomerReportDto> mapToCustomerReportDto(Collection<Order> orders) {
        return orders.stream().map(this::mapToCustomerReportDto).toList();
    }




}
