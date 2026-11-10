package org.switchfully.eurder.api.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.api.dto.CustomerReportResponse;
import org.switchfully.eurder.api.dto.OrderCustomerReportResponse;
import org.switchfully.eurder.api.dto.OrderResponse;
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

    public OrderResponse mapToDto(Order savedOrder, List<ItemGroup> itemGroups) {
        return new OrderResponse(savedOrder.getId(), savedOrder.getCustomerId(),
                itemGroupMapper.mapToDto(itemGroups), savedOrder.getTotalPrice());
    }

    public OrderCustomerReportResponse mapToOrderCustomerReportDto(Order order, List<ItemGroup> itemGroups) {
        return new OrderCustomerReportResponse(order.getId(),
                itemGroupMapper.mapToCustomerReportDto(itemGroups), order.getTotalPrice());
    }

    public CustomerReportResponse mapToCustomerReportDto(Collection<Order> orders, Map<String, List<ItemGroup>> itemGroupsPerOrder) {
        List<OrderCustomerReportResponse> orderCustomerReportResponses = orders.stream()
                .map(order -> {
                    List<ItemGroup> groupsForOrder = itemGroupsPerOrder.getOrDefault(order.getId(), List.of());
                    return this.mapToOrderCustomerReportDto(order, groupsForOrder);
                })
                .toList();
        double totalOfAllOrders = orders.stream()
                .mapToDouble(order -> order.getTotalPrice().doubleValue())
                .sum();
        return new CustomerReportResponse(orderCustomerReportResponses, totalOfAllOrders);
    }
}
