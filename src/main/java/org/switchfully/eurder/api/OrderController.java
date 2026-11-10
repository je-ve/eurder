package org.switchfully.eurder.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.switchfully.eurder.api.dto.CreateOrderDto;
import org.switchfully.eurder.api.dto.CustomerReportResponse;
import org.switchfully.eurder.api.dto.OrderResponse;
import org.switchfully.eurder.service.OrderService;

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
    public OrderResponse createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return orderService.createOrder(createOrderDto);
    }

    @GetMapping(path = "/customer/{id}", produces = "application/json")
    public CustomerReportResponse getItemById(@PathVariable String id) {
        return orderService.getCustomerReport(id);
    }


    @PostMapping(path = "/customer/{customerId}", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse reorderByOrderId(@PathVariable String customerId, @RequestBody String orderId) {
        logger.info("reorderByDto was called" + orderId);
        return orderService.reorderByOrderId(customerId, orderId);
    }
}



