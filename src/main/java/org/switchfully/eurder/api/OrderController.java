package org.switchfully.eurder.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.switchfully.eurder.api.dto.CreateOrderDto;
import org.switchfully.eurder.api.dto.CustomerReportDto;
import org.switchfully.eurder.api.dto.ItemDto;
import org.switchfully.eurder.api.dto.OrderDto;
import org.switchfully.eurder.service.OrderService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody CreateOrderDto createOrderDto) {
        //authorization
        return orderService.createOrder(createOrderDto);
    }

    @GetMapping(path = "/customer/{id}", produces = "application/json")
    public CustomerReportDto getItemById(@PathVariable String id) {
        return orderService.getCustomerReport(id);
    }

    @PostMapping(path = "/customer/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto reorderByOrderId(@PathVariable String customerId, @RequestBody String orderId) {
        return orderService.reorderByOrderId(customerId, orderId);
    }
}



