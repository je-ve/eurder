package org.switchfully.eurder.api.dto;

import org.switchfully.eurder.domain.Role;

public class UserDto {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final AddressDto address;
    private final String phoneNumber;
    private final Role role;

    public UserDto(String id, String firstName, String lastName, String email, AddressDto addressDto, String phoneNumber, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = addressDto;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

}
