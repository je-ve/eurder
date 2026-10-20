package org.switchfully.eurder.api.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.api.dto.CreateItemDto;
import org.switchfully.eurder.api.dto.ItemDto;
import org.switchfully.eurder.domain.Item;

import java.util.Collection;

@Component
public class ItemMapper {

    public Item mapToItem(CreateItemDto createItemDto) {
        return new Item(createItemDto.getName(),
                createItemDto.getDescription(),
                createItemDto.getPrice(),
                createItemDto.getStock());
    }

    public ItemDto mapToItemDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getStock());
    }

    public Collection<ItemDto> mapToItemDto(Collection<Item> items) {
        return items.stream().map(this::mapToItemDto).toList();
    }

}