package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ItemTileType;

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
     * Generator of a random number.
     */
    private final Random random;

    /**
     * Constructor for bag, initializing the bag with 22 item tile of each type, except for the empty type.
     */
    public Bag() {
        this.itemTiles = new ArrayList<>();
        this.random = new Random();
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

    /**
     * Get list of random item tile from the bag.
     * It also removes those item tile from the bag.
     *
     * @param number is the amount of item tiles to get and remove
     * @return list of item tile randomly picked
     */
    public List<ItemTile> getRandomItemTiles(int number) {
        if (number > this.itemTiles.size()) {
            throw new IllegalArgumentException("The parameter is greater than number of tiles in the bag");
        }
        return IntStream.range(0, number)
                .mapToObj(i -> this.itemTiles.remove(random.nextInt(this.itemTiles.size())))
                .collect(Collectors.toList());
    }
}
