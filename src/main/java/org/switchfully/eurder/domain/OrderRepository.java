package org.switchfully.eurder.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface OrderRepository extends JpaRepository<Order, String> {

    // SELECT * FROM orders WHERE customer_id = ?
    Collection<Order> findAllByCustomerId(String customerId);
}




/*

@Repository
public class OrderRepository {
    private final ConcurrentHashMap<String, Order> orders;

    public OrderRepository() {
        this.orders = new ConcurrentHashMap<>();
    }

    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }

    public Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    public Collection<Order> getAll() {
        return orders.values();
    }

    public Collection<Order> getByCustomerId(String customerId) {
        return orders.values().stream().filter(order -> order.getCustomerId().equals(customerId)).toList();
    }


}
 */

