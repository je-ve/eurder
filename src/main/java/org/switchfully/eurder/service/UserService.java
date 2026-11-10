package org.switchfully.eurder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.switchfully.eurder.api.dto.CreateCustomerRequest;
import org.switchfully.eurder.api.dto.UserResponse;
import org.switchfully.eurder.api.mapper.UserMapper;
import org.switchfully.eurder.domain.Role;
import org.switchfully.eurder.domain.User;
import org.switchfully.eurder.domain.UserRepository;
import org.switchfully.eurder.exception.CustomerNotFoundException;
import org.switchfully.eurder.exception.EmailAlreadyExistsException;

import java.util.Collection;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        validateEmailIsNotUsed(createCustomerRequest.getEmail());
        User customer = userMapper.mapToUser(createCustomerRequest);
        return userMapper.mapToUserDto(userRepository.save(customer));
    }

    public Collection<UserResponse> getAllCustomers() {
        return userMapper.mapToUserDto(userRepository.findAllByRole(Role.CUSTOMER));
    }

    public UserResponse getCustomerById(String id) {
        return userRepository.findCustomerById(id)
                .map(userMapper::mapToUserDto)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    private void validateEmailIsNotUsed(String email) {
        if (userRepository.existsByEmailIgnoreCase(email)) throw new EmailAlreadyExistsException(email);
    }


}
