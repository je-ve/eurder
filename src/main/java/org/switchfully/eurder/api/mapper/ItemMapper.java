package org.switchfully.eurder.api.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.api.dto.CreateItemRequest;
import org.switchfully.eurder.api.dto.ItemResponse;
import org.switchfully.eurder.domain.Item;

import java.util.Collection;

@Component
public class ItemMapper {

    public Item mapToItem(CreateItemRequest createItemRequest) {
        return new Item(createItemRequest.name(),
                createItemRequest.description(),
                createItemRequest.price(),
                createItemRequest.stock());
    }

    public ItemResponse mapToItemDto(Item item) {
        return new ItemResponse(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getStock());
    }

    public Collection<ItemResponse> mapToItemDto(Collection<Item> items) {
        return items.stream().map(this::mapToItemDto).toList();
    }

}