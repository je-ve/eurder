package org.switchfully.eurder.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.switchfully.eurder.api.dto.*;
import org.switchfully.eurder.api.mapper.AddressMapper;
import org.switchfully.eurder.api.mapper.ItemGroupMapper;
import org.switchfully.eurder.api.mapper.OrderMapper;
import org.switchfully.eurder.domain.*;
import org.switchfully.eurder.exception.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ItemGroupRepository itemGroupRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final ItemGroupMapper itemGroupMapper;
    private final AddressMapper addressMapper;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository,
                        ItemGroupRepository itemGroupRepository, UserRepository userRepository, OrderMapper orderMapper, ItemGroupMapper itemGroupMapper, AddressMapper addressMapper) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.itemGroupRepository = itemGroupRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
        this.itemGroupMapper = itemGroupMapper;
        this.addressMapper = addressMapper;
    }

    public OrderResponse createOrder(CreateOrderDto createOrderDto) {
        //create existById method
        User customer = userRepository.findById(createOrderDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(createOrderDto.getCustomerId()));

        //todo move security check to controller, use feature not role
        if (customer.getRole() != Role.CUSTOMER) {
            throw new ForbiddenException("Only registered customers can place orders.");
        }

        if (createOrderDto.getCreateItemGroupDtos() == null || createOrderDto.getCreateItemGroupDtos().isEmpty())
            throw new MissingItemGroupException();

        Order createdOrder = orderRepository.save(new Order(customer.getId(), calculateTotalForDtos(createOrderDto.getCreateItemGroupDtos())));

        List<ItemGroup> itemGroups = new ArrayList<>();
        for (CreateItemGroupRequest dto : createOrderDto.getCreateItemGroupDtos()) {
            Item item = itemRepository.findById(dto.getItemId()).orElseThrow(() -> new ItemNotFoundException(dto.getItemId()));
            ItemGroup itemGroup = new ItemGroup(item, dto.getAmount(), createdOrder);
            itemGroups.add(itemGroup);
        }
        itemGroupRepository.saveAll(itemGroups);

        //TODO update stock
        return orderMapper.mapToDto(createdOrder, itemGroups);
    }

    private BigDecimal calculateTotalForDtos(List<CreateItemGroupRequest> createItemGroupRequests) {
        return createItemGroupRequests.stream()
                .map(itemGroup -> {
                    Item item = itemRepository.findById(itemGroup.getItemId())
                            .orElseThrow(() -> new ItemNotFoundException(itemGroup.getItemId()));
                    return item.getPrice().multiply(BigDecimal.valueOf(itemGroup.getAmount()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotal(List<ItemGroup> itemGroups) {
        return itemGroups.stream()
                // Gebruik de methode die we in ItemGroup hebben gebouwd!
                .map(ItemGroup::calculateGroupPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public CustomerReportResponse getCustomerReport(String customerId) {

        if (!userRepository.existsByIdAndRole(customerId, Role.CUSTOMER))
            throw new CustomerNotFoundException(customerId);

        Collection<Order> orders = orderRepository.findAllByCustomerId(customerId);

        List<String> orderIds = orders.stream().map(Order::getId).toList();
        List<ItemGroup> allItemGroups = itemGroupRepository.findAllByOrderIdIn(orderIds);

        Map<String, List<ItemGroup>> itemGroupsPerOrder = allItemGroups.stream()
                .collect(Collectors.groupingBy(itemGroup -> itemGroup.getOrder().getId()));

        return orderMapper.mapToCustomerReportDto(orders, itemGroupsPerOrder);
    }


    public OrderResponse reorderByOrderId(String customerId, String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        if (!order.getCustomerId().equals(customerId))
            throw new NotFoundException("No order found for this id and this customer");

        List<ItemGroup> itemGroups = itemGroupRepository.findAllByOrderId(orderId).stream()
                .map(itemGroup -> new ItemGroup(
                        itemRepository.findById(itemGroup.getItemId())
                                .orElseThrow(() -> new ItemNotFoundException(itemGroup.getItemId())),
                        itemGroup.getAmount(),
                        order))
                .toList();


        return orderMapper.mapToDto(
                orderRepository.save(new Order(customerId, calculateTotal(itemGroups))),
                itemGroups
        );
    }

    //TODO after adding address to order, change address to address from order (not from customer)
    public List<ItemsGroupWithAddressResponse> getItemsShippingToday() {
        LocalDate today = LocalDate.now();
        List<ItemGroup> shippingToday = itemGroupRepository.findAllByShippingDate(today);

        return shippingToday.stream()
                .map(itemGroup -> new ItemsGroupWithAddressResponse(
                        itemGroupMapper.mapToDto(itemGroup),
                        addressMapper.mapToDto(userRepository.findCustomerById(itemGroup.getOrder().getCustomerId())
                                .orElseThrow(() -> new CustomerNotFoundException(itemGroup.getOrder().getCustomerId()))
                                .getAddress())
                ))
                .toList();
    }
}

