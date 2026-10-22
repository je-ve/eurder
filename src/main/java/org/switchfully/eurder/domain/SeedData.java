package org.switchfully.eurder.domain;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SeedData {
    private final static boolean ADD_SEED_DATA = true;

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    public SeedData(UserRepository userRepository, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    public static boolean addSeedData() {
        return ADD_SEED_DATA;
    }

    private final Address firstAddress = new Address("Grand place", "45", "1000", "Brussel");
    private final Address secondAddress = new Address("Graslei", "2", "9000", "Gent");

    private final User firstCustomer = User.createWithId("user-id-001", "Jan", "Jansens", "jan@janssens.com", firstAddress, "+32125302157", Role.CUSTOMER);
    private final User secondCustomer = User.createWithId("user-id-002", "Piet", "Pieters", "piet@pieters.com", secondAddress, "+32125302157", Role.CUSTOMER);
    private final User firstAdmin = User.createWithId("user-id-003", "Hans", "Hansens", "hans@hansens.com", firstAddress, "+32125302157", Role.ADMIN);

    private final Item firstItem = Item.createWithId("item-id-001", "computer", "a computer", 10.0, 10);
    private final Item secondItem = Item.createWithId("item-id-002", "table", "a table", 20.0, 20);
    private final Item thirdItem = Item.createWithId("item-id-003", "TV", "a TV", 5.0, 30);

    private final ItemGroup firstItemGroup = new ItemGroup(firstItem, 5);
    private final ItemGroup secondItemGroup = new ItemGroup(firstItem, 10);
    private final ItemGroup thirdItemGroup = new ItemGroup(secondItem, 2);
    private final ItemGroup fourthItemGroup = new ItemGroup(thirdItem, 3);

    private final Order firstOrder = Order.createWithId("order-id-001", "user-id-001", List.of(firstItemGroup, secondItemGroup));
    private final Order secondOrder = Order.createWithId("order-id-002", "user-id-002", List.of(thirdItemGroup, fourthItemGroup));

    @PostConstruct
    public void init() {
        if (addSeedData()) {
            // Sla de gebruikers op
            userRepository.save(firstCustomer);
            userRepository.save(secondCustomer);
            userRepository.save(firstAdmin);

            // Sla de items op
            itemRepository.save(firstItem);
            itemRepository.save(secondItem);
            itemRepository.save(thirdItem);

            // Sla de orders op
            orderRepository.save(firstOrder);
            orderRepository.save(secondOrder);
        }
    }
}