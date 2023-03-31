package it.polimi.ingsw.model.logic.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class representing the common goal card 10.
 */
public class CommonCard10 implements CommonGoalCard {

    /**
     * The index of the card.
     */
    private final int index;
    /**
     * The list of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 10, initializes index and points.
     *
     * @param index  is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard10(int index, List<Integer> points) {
        this.index = index;
        this.points = points;
    }

    /**
     * Get the index of the card.
     *
     * @return the index of the card
     */
    @Override
    public int getIndex() {
        return this.index;
    }

    /**
     * Get the points on the card.
     *
     * @return the points on the card
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
     * Check if the rules of the card are respected.
     *
     * @param libraryGrid is the library grid
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(ItemTile[][] libraryGrid) {
        int counter = 0;

        firstLoop:
        for (ItemTile[] itemTiles : libraryGrid) {
            Set<ItemTileType> distinctTypes = new HashSet<>();
            for (int col = 0; col < libraryGrid[0].length; col++) {
                ItemTileType currentType = itemTiles[col].getItemTileType();
                if (currentType == ItemTileType.EMPTY) {
                    continue firstLoop;
                }
                distinctTypes.add(currentType);
            }
            if (distinctTypes.size() == libraryGrid[0].length) {
                counter++;
            }
            if (counter == 2) {
                return true;
            }
        }
        return false;
    }
}
