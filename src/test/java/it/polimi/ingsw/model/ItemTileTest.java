package it.polimi.ingsw.model;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTileTest {

    @Test
    @DisplayName("Test that all types are assigned correctly")
    public void testGetItemTileType() {
        ItemTile itemTile = new ItemTile(ItemTileType.CAT);
        assertEquals(ItemTileType.CAT, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.BOOK);
        assertEquals(ItemTileType.BOOK, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.GAME);
        assertEquals(ItemTileType.GAME, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.FRAME);
        assertEquals(ItemTileType.FRAME, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.TROPHY);
        assertEquals(ItemTileType.TROPHY, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.PLANT);
        assertEquals(ItemTileType.PLANT, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.EMPTY);
        assertEquals(ItemTileType.EMPTY, itemTile.getItemTileType());
    }

    @Test
    @DisplayName("Test that all colors are assigned correctly")
    public void testGetItemTileColor() {
        ItemTile itemTile = new ItemTile(ItemTileType.CAT);
        assertEquals("GREEN", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.BOOK);
        assertEquals("WHITE", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.GAME);
        assertEquals("YELLOW", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.FRAME);
        assertEquals("BLUE", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.TROPHY);
        assertEquals("LIGHT_BLUE", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.PLANT);
        assertEquals("PINK", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.EMPTY);
        assertEquals("NONE", itemTile.getItemTileColor());
    }
}