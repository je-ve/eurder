package org.switchfully.eurder.domain;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {
    private final ConcurrentHashMap<String, Item> items;

    public ItemRepository() {
        this.items = new ConcurrentHashMap<>();
    }

    public Optional<Item> findById(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public Item save(Item item) {
        items.put(item.getId(), item);
        return item;
    }

    public Collection<Item> getAll() {
        return items.values();
    }

}
