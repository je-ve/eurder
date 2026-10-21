package org.switchfully.eurder.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        // Zorg voor een schone repository voor elke test
        itemRepository = new ItemRepository();
    }

    @Test
    void save_and_findById_happyPath() {
        // GIVEN
        Item item = new Item("item-1", "a laptop",999.99,  10);

        // WHEN
        Item savedItem = itemRepository.save(item);
        Optional<Item> foundItem = itemRepository.findById(savedItem.getId());

        // THEN
        assertThat(savedItem).isEqualTo(item);
        assertThat(foundItem).isPresent().contains(item);
    }

    @Test
    void findById_givenUnknownId_returnsEmptyOptional() {
        // WHEN
        Optional<Item> foundItem = itemRepository.findById("non-existing-id");

        // THEN
        assertThat(foundItem).isEmpty();
    }

    @Test
    void getAll_returnsAllSavedItems() {
        // GIVEN
        Item item1 = new Item("item-1", "a laptop",999.99,  10);
        Item item2 = new Item("item-2",  "a mouse", 49.99, 25);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // WHEN
        Collection<Item> allItems = itemRepository.getAll();

        // THEN
        assertThat(allItems)
                .hasSize(2)
                .containsExactlyInAnyOrder(item1, item2);
    }
}
