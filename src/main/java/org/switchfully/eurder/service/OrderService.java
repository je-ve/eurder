package org.switchfully.eurder.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.switchfully.eurder.api.dto.CreateItemGroupDto;
import org.switchfully.eurder.api.dto.CreateOrderDto;
import org.switchfully.eurder.api.dto.OrderDto;
import org.switchfully.eurder.api.mapper.OrderMapper;
import org.switchfully.eurder.domain.*;

import java.util.ArrayList;
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

    public OrderDto createOrder(String userId, CreateOrderDto createOrderDto) {
        //create existById method
        User customer = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        if (customer.getRole() != Role.CUSTOMER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only registered customers can place orders.");
        }

        if (createOrderDto.getCreateItemGroupDtos() == null || createOrderDto.getCreateItemGroupDtos().size() == 0)
            throw new MissingItemGroupException();

        List<ItemGroup> itemGroups = new ArrayList<>();
        for (CreateItemGroupDto dto : createOrderDto.getCreateItemGroupDtos()) {
            Item item = itemRepository.findById(dto.getItemId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item with ID " + dto.getItemId() + " does not exist."));

            ItemGroup itemGroup = new ItemGroup(item, dto.getAmount());
            itemGroups.add(itemGroup);
        }

        Order order = new Order(
                customer.getId(),
                itemGroups
        );

        orderRepository.save(order);
        return orderMapper.mapToDto(order);
    }
}

