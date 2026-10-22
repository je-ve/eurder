package org.switchfully.eurder.service;

import org.springframework.stereotype.Service;
import org.switchfully.eurder.api.dto.CreateItemGroupDto;
import org.switchfully.eurder.api.dto.CreateOrderDto;
import org.switchfully.eurder.api.dto.CustomerReportDto;
import org.switchfully.eurder.api.dto.OrderDto;
import org.switchfully.eurder.api.mapper.OrderMapper;
import org.switchfully.eurder.domain.*;
import org.switchfully.eurder.exception.CustomerNotFoundException;
import org.switchfully.eurder.exception.ForbiddenException;
import org.switchfully.eurder.exception.ItemNotFoundException;
import org.switchfully.eurder.exception.OrderNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, UserRepository userRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        //create existById method
        User customer = userRepository.findById(createOrderDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(createOrderDto.getCustomerId()));

        if (customer.getRole() != Role.CUSTOMER) {
            throw new ForbiddenException("Only registered customers can place orders.");
        }

        if (createOrderDto.getCreateItemGroupDtos() == null || createOrderDto.getCreateItemGroupDtos().isEmpty())
            throw new MissingItemGroupException();

        List<ItemGroup> itemGroups = new ArrayList<>();
        for (CreateItemGroupDto dto : createOrderDto.getCreateItemGroupDtos()) {
            Item item = itemRepository.findById(dto.getItemId()).orElseThrow(() -> new ItemNotFoundException(dto.getItemId()));
            ItemGroup itemGroup = new ItemGroup(item, dto.getAmount());
            itemGroups.add(itemGroup);
        }

        //TODO update stock
        return orderMapper.mapToDto(orderRepository.save(new Order(customer.getId(), itemGroups)));
    }

    public CustomerReportDto getCustomerReport(String customerId) {
        if (!userRepository.customerExistsByUserid(customerId)) throw new CustomerNotFoundException(customerId);
        Collection<Order> orders = orderRepository.getByCustomerId(customerId);
        return new CustomerReportDto(orderMapper.mapToCustomerReportDto(orders),
                orders.stream()
                        .mapToDouble(Order::getTotalPrice)
                        .sum());
    }

    public OrderDto reorderByOrderId(String customerId, String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        if(!order.getCustomerId().equals(customerId)) throw new ForbiddenException("No order found for this id and this customer");
        List<ItemGroup> itemGroups = order.getItemGroups().stream()
                .map(itemGroup -> new ItemGroup(
                        itemRepository.findById(itemGroup.getItemId())
                                .orElseThrow(() -> new ItemNotFoundException(itemGroup.getItemId())),
                        itemGroup.getAmount())).toList();
        return orderMapper.mapToDto(orderRepository.save(new Order(customerId, itemGroups)));
    }

}

