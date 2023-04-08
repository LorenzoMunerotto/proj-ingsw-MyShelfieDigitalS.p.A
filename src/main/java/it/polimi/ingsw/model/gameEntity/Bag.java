package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.*;

/**
 * Class representing the bag which contains all the itemTiles.
 */
public class Bag {

    /**
     * List of the item tiles im the bag.
     */
    private final List<ItemTile> itemTiles;


    /**
     * Constructor for bag, initializes the bag with 22 item tile of each type, except for the empty type.
     */
    public Bag() {
        this.itemTiles = new ArrayList<>();
        for (ItemTileType type : ItemTileType.values()) {
            if (type == ItemTileType.EMPTY) {
                continue;
            }
            for (int i = 0; i < 22; i++) {
                itemTiles.add(new ItemTile(type));
            }
        }
        Collections.shuffle(itemTiles);
    }

    /**
     * Get the list of item tiles in the bag.
     *
     * @return list of item tiles in the bag
     */
    public List<ItemTile> getItemTiles() {
        return this.itemTiles;
    }


    public ItemTile getRandomItemTile() {

        if (itemTiles.size()==0) {
            throw new IllegalArgumentException("The bag is empty");
        }
        ItemTile itemTile = itemTiles.remove(0);
        return itemTile;
    }

    public void shuffle(){
        Collections.shuffle(itemTiles);
    }
}
