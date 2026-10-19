package org.switchfully.eurder.controller.dto;

public class RegisterCustomerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressDto address;

    public RegisterCustomerDto() {
    }

    public RegisterCustomerDto(String firstName, String lastName, String email, String phoneNumber, AddressDto address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
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


    public String getPhoneNumber() {
        return phoneNumber;
    }
    public AddressDto getAddress() {
        return address;
    }
}

