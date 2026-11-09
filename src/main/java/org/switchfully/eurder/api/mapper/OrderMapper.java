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

    public OrderCustomerReportDto mapToCustomerReportDto(Order order, List<ItemGroup> itemGroups) {
        return new OrderCustomerReportDto(order.getId(),
                itemGroupMapper.mapToCustomerReportDto(itemGroups), order.getTotalPrice());
    }

    // Aangepaste methode: accepteert de orders én de voorgegroepeerde itemgroups
    public List<OrderCustomerReportDto> mapToCustomerReportDto(Collection<Order> orders, Map<String, List<ItemGroup>> itemGroupsPerOrder) {
        return orders.stream()
                .map(order -> {
                    // Haal de lijst met itemgroups op voor deze specifieke order (of een lege lijst als er geen zijn)
                    List<ItemGroup> groupsForOrder = itemGroupsPerOrder.getOrDefault(order.getId(), List.of());
                    return this.mapToCustomerReportDto(order, groupsForOrder);
                })
                .toList();
    }
}
