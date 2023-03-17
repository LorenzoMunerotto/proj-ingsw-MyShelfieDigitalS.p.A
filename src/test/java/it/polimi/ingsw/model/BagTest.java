package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    Bag bag;

    @BeforeEach
    void setUp() {
        bag = new Bag();
    }

    @Test
    @DisplayName("Test that the constructor of the class creates 22 elements of each type except the empty type")
    void testBagConstructor() {
        List<ItemTile> itemTileList = bag.getItemTiles();
        assertEquals(132, itemTileList.size());
        Map<ItemTileType, Integer> counts = new HashMap<>();

        for (ItemTile tile : itemTileList) {
            ItemTileType type = tile.getItemTileType();
            counts.put(type, counts.getOrDefault(type, 0) + 1);
            assertNotEquals(ItemTileType.EMPTY, tile.getItemTileType());
        }

        for (ItemTileType type : ItemTileType.values()) {
            if (type != ItemTileType.EMPTY) {
                assertEquals(22, counts.getOrDefault(type, 0).intValue());
            }
        }
    }

    @Test
    @DisplayName("Test getRandomItemTiles for a correct number as parameter")
    void testGetRandomItemTiles_correctNumber() {
        Random random = new Random();
        int originalNumber = bag.getItemTiles().size();
        int randomNumber = random.nextInt(originalNumber) + 1;
        List<ItemTile> randomItems = bag.getRandomItemTiles(randomNumber);
        assertAll(
                () -> assertEquals(randomNumber, randomItems.size()),
                () -> assertEquals(originalNumber - randomNumber, bag.getItemTiles().size()),
                () -> assertFalse(bag.getItemTiles().containsAll(randomItems))
        );
    }

    @Test
    @DisplayName("Test getRandomItemTiles for a wrong number as parameter")
    void testGetRandomItemTiles_wrongNumber() {
        Random random = new Random();
        int maxSize = bag.getItemTiles().size();
        int randomNumber = random.nextInt(Integer.MIN_VALUE - maxSize) + maxSize;
        assertThrows(IllegalArgumentException.class, () -> bag.getRandomItemTiles(randomNumber));
    }
}