package it.polimi.ingsw.model.logic.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A specific type of card that has a unique rule.
 */
public class CommonCard6 implements CommonGoalCard {

    /**
     * The index of the card in the deck.
     */
    private final int index;
    /**
     * List of point tiles.
     */
    private final List<Integer> points;

    /**
     * Constructor for card6, initializing the index and the points according to the number of players.
     *
     * @param index is the index of the card in the deck
     * @param points list of points tile
     */
    public CommonCard6(int index, List<Integer> points) {
        this.index = index;
        this.points = points;
    }

    /**
     * Get the index of the card.
     *
     * @return index of the card
     */
    @Override
    public int getIndex() {
        return this.index;
    }

    /**
     * Get the list of point tiles.
     *
     * @return points tile
     */
    @Override
    public List<Integer> getPoints() {
        return this.points;
    }

    /**
     * Get the first point on the card.
     * Also removes the point from the list of points.
     *
     * @return the first point on the card
     */
    @Override
    public int getHighestPoint(){
        return this.points.remove(0);
    }

    /**
     * Checks if the rules of the card are respected.
     *
     * @param library is the Library
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {
        Map<ItemTileType, Integer> itemTileTypeCounter = new HashMap<>();

        for (int row=0; row< library.getROWS(); row++) {
            for (int col = 0; col < library.getCOLUMNS(); col++) {
                ItemTileType currentItemTileType = library.getItemTile(row,col).getItemTileType();
                if (currentItemTileType != ItemTileType.EMPTY) {
                    itemTileTypeCounter.put(currentItemTileType, itemTileTypeCounter.getOrDefault(currentItemTileType, 0) + 1);
                    if (itemTileTypeCounter.get(currentItemTileType) >= 8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
