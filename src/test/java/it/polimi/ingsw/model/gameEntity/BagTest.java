package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<ItemTileType> itemTileList = bag.getItemTiles();
        assertEquals(132, itemTileList.size());
        Map<ItemTileType, Integer> counts = new HashMap<>();

        for (ItemTileType tile : itemTileList) {
            counts.put(tile, counts.getOrDefault(tile, 0) + 1);
            assertNotEquals(ItemTileType.EMPTY, tile);
        }

        for (ItemTileType type : ItemTileType.values()) {
            if (type != ItemTileType.EMPTY && type != ItemTileType.NULL) {
                assertEquals(22, counts.getOrDefault(type, 0).intValue());
            }
        }
    }


}