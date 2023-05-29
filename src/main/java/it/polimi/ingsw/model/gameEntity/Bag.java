package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameState.exceptions.EmptyBagException;

import java.util.*;

/**
 * Class representing the bag which contains all the itemTiles.
 */
public class Bag {

    private final static int ITEM_TILE_FOR_TYPE = 22;
    /**
     * List of the item tiles im the bag.
     */
    private final List<ItemTileType> itemTiles;

    /**
     * Constructor for bag, initializes the bag with 22 item tile of each type, except for the empty type.
     */
    public Bag() {
        this.itemTiles = new ArrayList<>();
        for (ItemTileType type : ItemTileType.values()) {
            if (type == ItemTileType.EMPTY || type == ItemTileType.NULL) {
                continue;
            }
            for (int i = 0; i < ITEM_TILE_FOR_TYPE; i++) {
                itemTiles.add(type);
            }
        }
    }

    /**
     * Get the list of item tiles in the bag.
     *
     * @return list of item tiles in the bag
     */
    public List<ItemTileType> getItemTiles() {
        return this.itemTiles;
    }

    /**
     * Get a random item tile from the bag.
     *
     * @return a random item tile from the bag
     */
    public ItemTileType grabItemTile() throws EmptyBagException {
        if (itemTiles.size() == 0) {
            throw new EmptyBagException();
        }
        return itemTiles.remove(0);
    }

    /**
     * This method shuffles the item tiles in the bag.
     */
    public void shuffle() {
        Collections.shuffle(itemTiles);
    }
}