package org.switchfully.eurder.service;

import org.switchfully.eurder.controller.dto.CreateCustomerDto;
import org.switchfully.eurder.controller.dto.UserDto;
import org.switchfully.eurder.domain.User;

public class ItemService {



    public UserDto createCustomer(CreateCustomerDto createCustomerDto) {
        User customer = userMapper.mapToUser(createCustomerDto);
        User savedCustomer = userRepository.save(customer);
        return userMapper.mapToUserDto(userRepository.save(savedCustomer));
    }

}
