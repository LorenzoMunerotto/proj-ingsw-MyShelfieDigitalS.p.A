package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ItemTileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTileTest {

    @Test
    @DisplayName("Test that all types are assigned correctly")
    public void testGetItemTileType() {
        ItemTile itemTile = new ItemTile(ItemTileType.CATS);
        assertEquals(ItemTileType.CATS, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.BOOKS);
        assertEquals(ItemTileType.BOOKS, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.GAMES);
        assertEquals(ItemTileType.GAMES, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.FRAMES);
        assertEquals(ItemTileType.FRAMES, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.TROPHIES);
        assertEquals(ItemTileType.TROPHIES, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.PLANTS);
        assertEquals(ItemTileType.PLANTS, itemTile.getItemTileType());

        itemTile = new ItemTile(ItemTileType.EMPTY);
        assertEquals(ItemTileType.EMPTY, itemTile.getItemTileType());
    }

    @Test
    @DisplayName("Test that all colors are assigned correctly")
    public void testGetItemTileColor() {
        ItemTile itemTile = new ItemTile(ItemTileType.CATS);
        assertEquals("GREEN", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.BOOKS);
        assertEquals("WHITE", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.GAMES);
        assertEquals("YELLOW", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.FRAMES);
        assertEquals("BLUE", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.TROPHIES);
        assertEquals("LIGHT_BLUE", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.PLANTS);
        assertEquals("PINK", itemTile.getItemTileColor());

        itemTile = new ItemTile(ItemTileType.EMPTY);
        assertEquals("NONE", itemTile.getItemTileColor());
    }
}