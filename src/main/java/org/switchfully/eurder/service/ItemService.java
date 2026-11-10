package org.switchfully.eurder.service;

import org.springframework.stereotype.Service;
import org.switchfully.eurder.api.dto.CreateItemRequest;
import org.switchfully.eurder.api.dto.ItemResponse;
import org.switchfully.eurder.api.dto.UpdateItemRequest;
import org.switchfully.eurder.api.mapper.ItemMapper;
import org.switchfully.eurder.domain.Item;
import org.switchfully.eurder.domain.ItemRepository;
import org.switchfully.eurder.exception.ItemNotFoundException;

import java.util.Collection;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    private ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemResponse getItemById(String id) {
        return itemRepository.findById(id)
                .map(itemMapper::mapToItemDto)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Collection<ItemResponse> getAllItems() {
        return itemMapper.mapToItemDto(itemRepository.findAll());
    }

    public ItemResponse createItem(CreateItemRequest createItemRequest) {
        Item item = itemMapper.mapToItem(createItemRequest);
        return itemMapper.mapToItemDto(itemRepository.save(item));
    }

    public ItemResponse updateItem(String itemId, UpdateItemRequest updateItemRequest) {
        Item itemToUpdate = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
        itemToUpdate.setName(updateItemRequest.name());
        itemToUpdate.setDescription(updateItemRequest.description());
        itemToUpdate.setPrice(updateItemRequest.price());
        itemToUpdate.setStock(updateItemRequest.stock());
        return itemMapper.mapToItemDto(itemRepository.save(itemToUpdate));
    }


}
