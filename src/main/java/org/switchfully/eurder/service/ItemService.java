package org.switchfully.eurder.service;

import org.springframework.stereotype.Service;
import org.switchfully.eurder.api.dto.CreateItemDto;
import org.switchfully.eurder.api.dto.ItemDto;
import org.switchfully.eurder.api.dto.UpdateItemDto;
import org.switchfully.eurder.api.dto.UserDto;
import org.switchfully.eurder.api.mapper.ItemMapper;
import org.switchfully.eurder.domain.Item;
import org.switchfully.eurder.domain.ItemRepository;
import org.switchfully.eurder.exception.CustomerNotFoundException;
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

    public ItemDto getItemById(String id) {
        return itemRepository.findById(id)
                .map(itemMapper::mapToItemDto)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Collection<ItemDto> getAllItems() {
        return itemMapper.mapToItemDto(itemRepository.getAll());
    }

    public ItemDto createItem(CreateItemDto createItemDto) {
        Item item = itemMapper.mapToItem(createItemDto);
        return itemMapper.mapToItemDto(itemRepository.save(item));
    }

    public ItemDto updateItem(String itemId, UpdateItemDto updateItemDto) {
        Item itemToUpdate = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
        itemToUpdate.setName(updateItemDto.getName());
        itemToUpdate.setDescription(updateItemDto.getDescription());
        itemToUpdate.setPrice(updateItemDto.getPrice());
        itemToUpdate.setStock(updateItemDto.getStock());
        return itemMapper.mapToItemDto(itemRepository.save(itemToUpdate));
    }



}
