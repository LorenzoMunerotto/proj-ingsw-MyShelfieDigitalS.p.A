package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing the item tile.
 */
public class ItemTile {

    /**
     * Type of the item tile.
     */
    private final ItemTileType itemTileType;

    /**
     * Constructor for item tile, initializes item tile EMPTY.
     */
    public ItemTile() {
        this.itemTileType = ItemTileType.EMPTY;
    }

    /**
     * Constructor for item tile, initializes item tile type.
     */
    public ItemTile(ItemTileType type){
        this.itemTileType = type;
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
