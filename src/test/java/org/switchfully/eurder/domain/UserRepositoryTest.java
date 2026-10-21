package org.switchfully.eurder.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    private UserRepository userRepository;
    private Address defaultAddress;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        defaultAddress = new Address("Stationstreet", "42", "1000", "Brussel");
    }

    @Test
    void save_and_findById_happyPath() {
        // GIVEN
        User user = new User("John", "Doe", "john@doe.com", defaultAddress, "0477123456", Role.CUSTOMER);

        // WHEN
        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findById(user.getId());

        // THEN
        assertThat(savedUser).isEqualTo(user);
        assertThat(foundUser).isPresent().contains(user);
    }

    @Test
    void findCustomerById_givenCustomer_returnsCustomer() {
        // GIVEN
        User customer = new User("Alice", "Smith", "alice@test.com", defaultAddress, "0477112233", Role.CUSTOMER);
        userRepository.save(customer);

        // WHEN
        Optional<User> foundCustomer = userRepository.findCustomerById(customer.getId());

        // THEN
        assertThat(foundCustomer).isPresent().contains(customer);
    }

    @Test
    void findCustomerById_givenAdmin_returnsEmptyOptional() {
        // GIVEN
        User admin = new User("Bob", "Jones", "bob@admin.com", defaultAddress, "0477445566", Role.ADMIN);
        userRepository.save(admin);

        // WHEN
        Optional<User> foundCustomer = userRepository.findCustomerById(admin.getId());

        // THEN
        assertThat(foundCustomer).isEmpty();
    }

    @Test
    void getAll_returnsAllSavedUsers() {
        // GIVEN
        User customer = new User("Alice", "Smith", "alice@test.com", defaultAddress, "0477112233", Role.CUSTOMER);
        User admin = new User("Bob", "Jones", "bob@admin.com", defaultAddress, "0477445566", Role.ADMIN);
        userRepository.save(customer);
        userRepository.save(admin);

        // WHEN
        Collection<User> allUsers = userRepository.getAll();

        // THEN
        assertThat(allUsers).hasSize(2).containsExactlyInAnyOrder(customer, admin);
    }

    @Test
    void getAllCustomers_returnsOnlyCustomers() {
        // GIVEN
        User customer1 = new User("Alice", "Smith", "alice@test.com", defaultAddress, "0477112233", Role.CUSTOMER);
        User customer2 = new User("Charlie", "Brown", "charlie@test.com", defaultAddress, "0477998877", Role.CUSTOMER);
        User admin = new User("Bob", "Jones", "bob@admin.com", defaultAddress, "0477445566", Role.ADMIN);

        userRepository.save(customer1);
        userRepository.save(customer2);
        userRepository.save(admin);

        // WHEN
        Collection<User> customers = userRepository.getAllCustomers();

        // THEN
        assertThat(customers).hasSize(2).containsExactlyInAnyOrder(customer1, customer2);
    }

    @Test
    void existByEmail_returnsTrue_whenEmailExistsCaseInsensitive() {
        // GIVEN
        User user = new User("John", "Doe", "John@Doe.com", defaultAddress, "0477123456", Role.CUSTOMER);
        userRepository.save(user);

        // WHEN & THEN
        assertThat(userRepository.existByEmail("john@doe.com")).isTrue();
        assertThat(userRepository.existByEmail("JOHN@DOE.COM")).isTrue();
    }

    @Test
    void existByEmail_returnsFalse_whenEmailDoesNotExist() {
        // WHEN & THEN
        assertThat(userRepository.existByEmail("notfound@test.com")).isFalse();
    }
}