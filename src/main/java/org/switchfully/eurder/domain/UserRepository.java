package org.switchfully.eurder.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    default Optional<User> findCustomerById(String id) {
        return findById(id).filter(u -> u.getRole() == Role.CUSTOMER);
    }

    Collection<User> findAllByRole(Role role);

    boolean existsByEmailIgnoreCase(String email);

    Optional<User> findByEmailIgnoreCase(String email);

    //SELECT COUNT(1) FROM users WHERE id = ? AND role = 'CUSTOMER'
    boolean existsByIdAndRole(String id, Role role);
}
