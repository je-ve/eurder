package org.switchfully.eurder.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
public interface UserRepository extends JpaRepository<User, String> {

    default Optional<User> findCustomerById(String id) {
        return findById(id).filter(u -> u.getRole() == Role.CUSTOMER);
    }

    Collection<User> findAllByRole(Role role);
    boolean existsByEmailIgnoreCase(String email);
    //SELECT COUNT(1) FROM users WHERE id = ? AND role = 'CUSTOMER'
    boolean existsByIdAndRole(String id, Role role);
}

/*
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

    public Optional<User> getByEmail(String email) {
        return users.values().stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public boolean customerExistsByUserid(String customerId) {
        return users.values().stream().anyMatch(user -> user.getRole() == Role.CUSTOMER
        &&  user.getId().equals(customerId));
    }
}
 */
