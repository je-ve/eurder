package org.switchfully.eurder.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.switchfully.eurder.api.dto.CreateCustomerDto;
import org.switchfully.eurder.api.dto.UserDto;
import org.switchfully.eurder.api.mapper.UserMapper;
import org.switchfully.eurder.domain.User;
import org.switchfully.eurder.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping(path="/customers")
public class CustomerController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerAsCustomer(@RequestBody CreateCustomerDto dto) {
        logger.info("register customer was called");
        UserDto createdUser = userService.createCustomer(dto);
        return createdUser;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    //public Collection<UserDto> getAllCustomers(@RequestHeader(value = "Authorization", required = false) String authorization){
    public Collection<UserDto> getAllCustomers() {
        logger.info("getALlcustomers was called");
        //securityService.validateAuthorization(authorization, Feature.VIEW_MEMBERS);
        return userService.getAllCustomers();
    }

    @GetMapping(produces = "application/json", path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getById(@RequestParam("id") String id) {
        return userService.getCustomerById(id);
    }

}


