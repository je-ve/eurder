package org.switchfully.eurder.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switchfully.eurder.api.dto.CreateItemRequest;
import org.switchfully.eurder.api.dto.ItemResponse;
import org.switchfully.eurder.api.mapper.ItemMapper;
import org.switchfully.eurder.domain.Item;
import org.switchfully.eurder.domain.ItemRepository;
import org.switchfully.eurder.exception.ItemNotFoundException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemService itemService;

    @Test
    void createItem_happyPath() {
        // GIVEN
        CreateItemRequest createItemRequest = new CreateItemRequest("Laptop", "MacBook Pro", new BigDecimal("1999.99"), 5);
        Item testItem = new Item("Laptop", "MacBook Pro", new BigDecimal("1999.99"), 5);
        ItemResponse expectedDto = new ItemResponse(testItem.getId(), "Laptop", "MacBook Pro", new BigDecimal("1999.99"), 5);

        when(itemMapper.mapToItem(createItemRequest)).thenReturn(testItem);
        when(itemRepository.save(testItem)).thenReturn(testItem);
        when(itemMapper.mapToItemDto(testItem)).thenReturn(expectedDto);

        // WHEN
        ItemResponse result = itemService.createItem(createItemRequest);

        // THEN
        assertThat(result).isEqualTo(expectedDto);
        verify(itemRepository).save(testItem);
    }

    @Test
    void getItemById_happyPath() {
        // GIVEN
        String itemId = "test-uuid-123";
        Item itemMock = mock(Item.class);
        ItemResponse expectedDto = new ItemResponse(itemId, "Mouse", "Ergonomic", new BigDecimal("49.99"), 10);

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(itemMock));
        when(itemMapper.mapToItemDto(itemMock)).thenReturn(expectedDto);

        // WHEN
        ItemResponse result = itemService.getItemById(itemId);

        // THEN
        assertThat(result).isEqualTo(expectedDto);
    }

    @Test
    void getItemById_itemNotFound_throwsException() {
        // GIVEN
        String unknownId = "unknown-id";
        when(itemRepository.findById(unknownId)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThatThrownBy(() -> itemService.getItemById(unknownId))
                .isInstanceOf(ItemNotFoundException.class);

        verifyNoInteractions(itemMapper);
    }

    @Test
    void getAllItems_happyPath() {
        // GIVEN
        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);
        List<Item> itemsList = List.of(item1, item2);

        ItemResponse dto1 = new ItemResponse("id-1", "Item 1", "Desc 1", new BigDecimal("10.0"), 2);
        ItemResponse dto2 = new ItemResponse("id-2", "Item 2", "Desc 2", new BigDecimal("20.0"), 4);
        Collection<ItemResponse> expectedDtos = List.of(dto1, dto2);

        when(itemRepository.findAll()).thenReturn(itemsList);
        when(itemMapper.mapToItemDto(itemsList)).thenReturn(expectedDtos);

        // WHEN
        Collection<ItemResponse> result = itemService.getAllItems();

        // THEN
        assertThat(result)
                .hasSize(2)
                .containsExactlyInAnyOrder(dto1, dto2);
    }
}