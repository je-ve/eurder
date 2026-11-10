package org.switchfully.eurder.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private String customerId;
    private BigDecimal totalPrice;

    public Order(String customerId,BigDecimal totalPrice) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.totalPrice = totalPrice;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }
    public String getCustomerId() {
        return customerId;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }


    //SEED
    public static Order createWithId(String id, String customerId, BigDecimal totalPrice) {
        return new Order(id, customerId, totalPrice);
    }

    private Order(String id, String customerId, BigDecimal totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
    }
}
