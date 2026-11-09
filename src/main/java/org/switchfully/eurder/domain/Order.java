package org.switchfully.eurder.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order")
public class Order {
    @Id
    private String id;
    private String customerId;
    private double totalPrice;

    public Order(String customerId,double totalPrice) {
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
    public double getTotalPrice() {
        return totalPrice;
    }


    //SEED
    public static Order createWithId(String id, String customerId, double totalPrice) {
        return new Order(id, customerId, totalPrice);
    }

    private Order(String id, String customerId, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
    }
}
