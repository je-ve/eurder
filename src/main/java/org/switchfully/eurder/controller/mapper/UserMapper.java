package org.switchfully.eurder.controller.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.controller.dto.CreateCustomerDto;
import org.switchfully.eurder.domain.User;

@Component
public class UserMapper {

    public User mapToUser(CreateCustomerDto createCustomerDto){
            return new User(createCustomerDto.getFirstName(),
                    createCustomerDto.getLastName(),
                    createCustomerDto.getEmail()),
                    createCustomerDto.getAddress()
    }

}
