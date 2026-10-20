package org.switchfully.eurder.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.switchfully.eurder.api.dto.CreateCustomerDto;
import org.switchfully.eurder.api.dto.UserDto;
import org.switchfully.eurder.api.mapper.UserMapper;
import org.switchfully.eurder.service.UserService;

import java.util.Collection;

public class CustomerController {

    private final UserMapper userMapper;
    private final UserService userService;


    public CustomerController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerAsCustomer(@RequestBody CreateCustomerDto dto) {
        return userService.createCustomer(dto);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    //public Collection<UserDto> getAllCustomers(@RequestHeader(value = "Authorization", required = false) String authorization){
    public Collection<UserDto> getAllCustomers() {
        //securityService.validateAuthorization(authorization, Feature.VIEW_MEMBERS);
        return userService.getAllCustomers();
    }

    @GetMapping(produces = "application/json", path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getById(@RequestParam("id") String id) {
        return userService.getCustomerById(id);
    }

}


