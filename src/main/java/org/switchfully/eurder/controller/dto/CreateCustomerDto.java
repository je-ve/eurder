package org.switchfully.eurder.controller.dto;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.domain.Address;
import org.switchfully.eurder.domain.Role;


public class CreateCustomerDto {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final AddressDto addressDto;
    private final String phoneNumber;

    public CreateCustomerDto(String firstName, String lastName, String email, AddressDto addressDto, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressDto = addressDto;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
