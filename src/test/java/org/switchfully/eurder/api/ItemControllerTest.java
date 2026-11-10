package org.switchfully.eurder.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.switchfully.eurder.api.dto.CreateItemRequest;
import org.switchfully.eurder.api.dto.ItemResponse;
import org.switchfully.eurder.service.ItemService;

import java.math.BigDecimal;
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
        ItemResponse item1 = new ItemResponse("id-1", "Laptop", "Apple M1", new BigDecimal("1200.00"), 5);
        ItemResponse item2 = new ItemResponse("id-2", "Mouse", "Logitech MX", new BigDecimal("80.00"), 10);
        when(itemService.getAllItems()).thenReturn(List.of(item1, item2));

        // WHEN
        Collection<ItemResponse> result = itemController.getAll();

        // THEN
        assertThat(result).containsExactlyInAnyOrder(item1, item2);
        verify(itemService).getAllItems();
    }

    @Test
    void getItemById_delegatesToService() {
        // GIVEN
        String itemId = "id-123";
        ItemResponse expectedDto = new ItemResponse(itemId, "Keyboard", "Mechanical", new BigDecimal("150.00"), 3);
        when(itemService.getItemById(itemId)).thenReturn(expectedDto);

        // WHEN
        ItemResponse result = itemController.getItemById(itemId);

        // THEN
        assertThat(result).isEqualTo(expectedDto);
        verify(itemService).getItemById(itemId);
    }

    @Test
    void createItem_callsServiceAndReturnsCreatedItem() {
        // GIVEN
        CreateItemRequest createItemRequest = new CreateItemRequest("Monitor", "4K Display", new BigDecimal("350.00"), 2);
        ItemResponse savedDto = new ItemResponse("new-uuid", "Monitor", "4K Display", new BigDecimal("350.00"), 2);
        when(itemService.createItem(createItemRequest)).thenReturn(savedDto);

        // WHEN
        ItemResponse result = itemController.createItem(createItemRequest);

        // THEN
        assertThat(result).isEqualTo(savedDto);
        verify(itemService).createItem(createItemRequest);
    }
}
