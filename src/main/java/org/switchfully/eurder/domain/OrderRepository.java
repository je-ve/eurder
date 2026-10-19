package org.switchfully.eurder.domain;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {
    private final ConcurrentHashMap<String, Order> orders;

    public OrderRepository() {
        this.orders = new ConcurrentHashMap<>();
    }

    Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }

    public Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    public Collection<Order> getAll() {
        return orders.values();
    }
}


