package org.switchfully.eurder.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.switchfully.eurder.api.dto.CreateItemRequest;
import org.switchfully.eurder.api.dto.ItemResponse;
import org.switchfully.eurder.api.dto.UpdateItemRequest;
import org.switchfully.eurder.service.ItemService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private ItemService itemService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(produces = "application/json")
    public Collection<ItemResponse> getAll() {
        logger.info("get all items called");
        return itemService.getAllItems();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ItemResponse getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse createItem(@RequestBody CreateItemRequest createItemRequest) {
        return itemService.createItem(createItemRequest);
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemResponse updateItem(@PathVariable String id, @RequestBody UpdateItemRequest updateItemRequest)
    { return itemService.updateItem(id, updateItemRequest);}
}
