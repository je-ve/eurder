package org.switchfully.eurder.service;

import org.springframework.stereotype.Service;
import org.switchfully.eurder.api.dto.CreateCustomerDto;
import org.switchfully.eurder.api.dto.UserDto;
import org.switchfully.eurder.api.mapper.UserMapper;
import org.switchfully.eurder.domain.User;
import org.switchfully.eurder.domain.UserRepository;
import org.switchfully.eurder.exception.CustomerNotFoundException;
import org.switchfully.eurder.exception.EmailAlreadyExistsException;

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
        validateEmailIsNotUsed(createCustomerDto.getEmail());
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
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    private void validateEmailIsNotUsed(String email) {
        if (userRepository.existByEmail(email)) throw new EmailAlreadyExistsException(email);
    }


}
