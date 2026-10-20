package org.switchfully.eurder.domain;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final ConcurrentHashMap<String, User> users;

    public UserRepository() {
        this.users = new ConcurrentHashMap<>();
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> findCustomerById(String id) {
        return findById(id).filter(u -> u.getRole() == Role.CUSTOMER);
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public Collection<User> getAllCustomers() {
        return users.values().stream().filter(user -> user.getRole() == Role.CUSTOMER).toList();
    }

    public boolean existByEmail(String email) {
        return users.values().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }
}
