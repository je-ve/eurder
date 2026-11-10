package org.switchfully.eurder.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Embedded
    private Address address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String firstName, String lastName, String email, Address address, String phoneNumber, Role role) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.password = "password";
    }

    public User() {
    }

    public String getId() {
        return id;
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

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }

    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }

    //FOR SEEDING
    public static User createWithId(String id, String firstName, String lastName, String email,
                                    Address address, String phoneNumber, Role role) {
        return new User(id, firstName, lastName, email, address, phoneNumber, role);
    }

    private User(String id, String firstName, String lastName, String email, Address address, String phoneNumber, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = "password";
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        // Twee gebruikers zijn gelijk als hun unieke ID gelijk is
        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}

