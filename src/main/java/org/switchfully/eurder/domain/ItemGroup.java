package org.switchfully.eurder.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "item_groups")
public class ItemGroup {

    @Id
    private String id;
    private String itemId;
    private String name;
    private int amount;
    private double pricePerUnit;
    private LocalDate shippingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public ItemGroup(Item item, int amount, Order order) {
        this.id = UUID.randomUUID().toString();
        this.itemId = item.getId();
        this.name = item.getName();
        this.amount = amount;
        this.pricePerUnit = item.getPrice();
        this.order = order;

        if (item.getStock() >= amount) {
            this.shippingDate = LocalDate.now().plusDays(1);
        } else {
            this.shippingDate = LocalDate.now().plusDays(7);
        }
    }

    public ItemGroup() {
    }

    public double calculateGroupPrice() {
        return this.pricePerUnit * this.amount;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
