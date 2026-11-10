package org.switchfully.eurder.api.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.api.dto.CreateCustomerRequest;
import org.switchfully.eurder.api.dto.UserResponse;
import org.switchfully.eurder.domain.Role;
import org.switchfully.eurder.domain.User;

import java.util.Collection;

@Component
public class UserMapper {

    private AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public User mapToUser(CreateCustomerRequest createCustomerRequest) {
        return new User(createCustomerRequest.getFirstName(),
                createCustomerRequest.getLastName(),
                createCustomerRequest.getEmail(),
                addressMapper.mapToAddress(createCustomerRequest.getAddressDto()),
                createCustomerRequest.getPhoneNumber(),
                Role.CUSTOMER);
    }

    public UserResponse mapToUserDto(User user) {
        return new UserResponse(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                addressMapper.mapToDto(user.getAddress()),
                user.getPhoneNumber(), user.getRole());
    }

    public Collection<UserResponse> mapToUserDto(Collection<User> users) {
        return users.stream().map(this::mapToUserDto).toList();
    }

}
