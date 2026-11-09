package org.switchfully.eurder.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="users")
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

    public String getPassword() {
        return password;
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


}

