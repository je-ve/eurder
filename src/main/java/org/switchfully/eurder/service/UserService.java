package org.switchfully.eurder.service;

import org.springframework.stereotype.Service;
import org.switchfully.eurder.controller.dto.CreateCustomerDto;
import org.switchfully.eurder.controller.dto.UserDto;
import org.switchfully.eurder.controller.mapper.UserMapper;
import org.switchfully.eurder.domain.User;
import org.switchfully.eurder.domain.UserRepository;

import java.util.Collection;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto createCustomer(CreateCustomerDto createCustomerDto) {
        User customer = userMapper.mapToUser(createCustomerDto);
        User savedCustomer = userRepository.save(customer);
        return userMapper.mapToUserDto(userRepository.save(savedCustomer));
    }

    public Collection<UserDto> getAllCustomers() {
        return userMapper.mapToUserDto(userRepository.getAllCustomers());
    }

    public UserDto getCustomerById(String id) {
        return userRepository.findCustomerById(id)
                .map(userMapper::mapToUserDto)
                .orElseThrow(() -> new IllegalArgumentException("Could not find customer with id " + id));
    }


}
