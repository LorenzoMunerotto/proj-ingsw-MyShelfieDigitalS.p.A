package it.polimi.ingsw.model.data;

import it.polimi.ingsw.model.data.enums.ItemTileType;

/**
 * Class representing the item tile.
 */
public class ItemTile {

    /**
     * Type of the item tile.
     */
    private final ItemTileType itemTileType;

    /**
     * Constructor for item tile, initializing item tile type.
     *
     * @param itemTileType the type of the item tile
     */
    public ItemTile(ItemTileType itemTileType) {
        this.itemTileType = itemTileType;
    }

    /**
     * Get the type of the item tile.
     *
     * @return the type of the item tile
     */
    public ItemTileType getItemTileType() {
        return this.itemTileType;
    }

    /**
     * Get the color of the item tile.
     *
     * @return the color of the item tile
     */
    public String getItemTileColor() {
        return this.itemTileType.getColor();
    }
}
