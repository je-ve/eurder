package org.switchfully.eurder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.switchfully.eurder.controller.dto.CreateOrderDto;
import org.switchfully.eurder.controller.dto.OrderDto;
import org.switchfully.eurder.service.OrderService;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder( @RequestBody CreateOrderDto createOrderDto){
        //authorization
        return orderService.createOrder("userId", createOrderDto);
    }
}
