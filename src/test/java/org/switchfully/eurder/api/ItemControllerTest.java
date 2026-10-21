package org.switchfully.eurder.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.switchfully.eurder.api.dto.CreateItemDto;
import org.switchfully.eurder.api.dto.ItemDto;
import org.switchfully.eurder.service.ItemService;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ItemControllerTest {

    private ItemService itemService;
    private ItemController itemController;

    @BeforeEach
    void setUp() {
        itemService = Mockito.mock(ItemService.class);
        itemController = new ItemController(itemService);
    }

    @Test
    void getAll_returnsItemsFromService() {
        // GIVEN
        ItemDto item1 = new ItemDto("id-1", "Laptop", "Apple M1", 1200.00, 5);
        ItemDto item2 = new ItemDto("id-2", "Mouse", "Logitech MX", 80.00, 10);
        when(itemService.getAllItems()).thenReturn(List.of(item1, item2));

        // WHEN
        Collection<ItemDto> result = itemController.getAll();

        // THEN
        assertThat(result).containsExactlyInAnyOrder(item1, item2);
        verify(itemService).getAllItems();
    }

    @Test
    void getItemById_delegatesToService() {
        // GIVEN
        String itemId = "id-123";
        ItemDto expectedDto = new ItemDto(itemId, "Keyboard", "Mechanical", 150.00, 3);
        when(itemService.getItemById(itemId)).thenReturn(expectedDto);

        // WHEN
        ItemDto result = itemController.getItemById(itemId);

        // THEN
        assertThat(result).isEqualTo(expectedDto);
        verify(itemService).getItemById(itemId);
    }

    @Test
    void createItem_callsServiceAndReturnsCreatedItem() {
        // GIVEN
        CreateItemDto createItemDto = new CreateItemDto("Monitor", "4K Display", 350.00, 2);
        ItemDto savedDto = new ItemDto("new-uuid", "Monitor", "4K Display", 350.00, 2);
        when(itemService.createItem(createItemDto)).thenReturn(savedDto);

        // WHEN
        ItemDto result = itemController.createItem(createItemDto);

        // THEN
        assertThat(result).isEqualTo(savedDto);
        verify(itemService).createItem(createItemDto);
    }
}
