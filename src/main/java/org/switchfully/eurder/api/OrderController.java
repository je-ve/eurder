package org.switchfully.eurder.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return orderService.createOrder(createOrderDto);
    }

    @GetMapping(path = "/customer/{id}", produces = "application/json")
    public CustomerReportDto getItemById(@PathVariable String id) {
        return orderService.getCustomerReport(id);
    }

    @PostMapping(path = "/customer/{customerId}", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto reorderByOrderId(@PathVariable String customerId, @RequestBody String orderId) {
        logger.info("reorderByDto was called" + orderId);
        return orderService.reorderByOrderId(customerId, orderId);
    }
}



