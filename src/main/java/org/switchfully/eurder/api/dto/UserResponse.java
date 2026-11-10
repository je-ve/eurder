package org.switchfully.eurder.api.dto;

import org.switchfully.eurder.domain.Role;

public record UserResponse(String id, String firstName, String lastName, String email, AddressDto address,
                           String phoneNumber, Role role) {
}
