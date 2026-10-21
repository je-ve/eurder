package org.switchfully.eurder.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.switchfully.eurder.api.dto.CreateItemDto;
import org.switchfully.eurder.api.dto.ItemDto;
import org.switchfully.eurder.api.dto.UpdateItemDto;
import org.switchfully.eurder.service.ItemService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private ItemService itemService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(produces = "application/json")
    public Collection<ItemDto> getAll() {
        logger.info("get all items called");
        return itemService.getAllItems();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ItemDto getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestBody CreateItemDto createItemDto) {
        return itemService.createItem(createItemDto);
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto updateItem(@PathVariable String id, @RequestBody UpdateItemDto updateItemDto)
    { return itemService.updateItem(id, updateItemDto);}
}
