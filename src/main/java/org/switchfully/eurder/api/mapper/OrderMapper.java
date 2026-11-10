package org.switchfully.eurder.api.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.api.dto.CustomerReportDto;
import org.switchfully.eurder.api.dto.OrderCustomerReportDto;
import org.switchfully.eurder.api.dto.OrderDto;
import org.switchfully.eurder.domain.ItemGroup;
import org.switchfully.eurder.domain.Order;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class OrderMapper {

    private ItemGroupMapper itemGroupMapper;

    public OrderMapper(ItemGroupMapper itemGroupMapper) {
        this.itemGroupMapper = itemGroupMapper;
    }

    public OrderDto mapToDto(Order savedOrder, List<ItemGroup> itemGroups) {
        return new OrderDto(savedOrder.getId(), savedOrder.getCustomerId(),
                itemGroupMapper.mapToDto(itemGroups), savedOrder.getTotalPrice());
    }

    public OrderCustomerReportDto mapToOrderCustomerReportDto(Order order, List<ItemGroup> itemGroups) {
        return new OrderCustomerReportDto(order.getId(),
                itemGroupMapper.mapToCustomerReportDto(itemGroups), order.getTotalPrice());
    }

    public CustomerReportDto mapToCustomerReportDto(Collection<Order> orders, Map<String, List<ItemGroup>> itemGroupsPerOrder) {
        List<OrderCustomerReportDto> orderCustomerReportDtos = orders.stream()
                .map(order -> {
                    List<ItemGroup> groupsForOrder = itemGroupsPerOrder.getOrDefault(order.getId(), List.of());
                    return this.mapToOrderCustomerReportDto(order, groupsForOrder);
                })
                .toList();
        double totalOfAllOrders = orders.stream()
                .mapToDouble(order -> order.getTotalPrice().doubleValue())
                .sum();
        return new CustomerReportDto(orderCustomerReportDtos, totalOfAllOrders);
    }
}
