package it.polimi.ingsw.model.logic.common_cards;

import it.polimi.ingsw.model.data.ItemTile;

import java.util.List;

/**
 * Class representing the common goal card 3.
 */
public class CommonCard3 implements CommonGoalCard {

    /**
     * The index of the card.
     */
    private final int index;
    /**
     * The list of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 3, initializes index and points.
     *
     * @param index  is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard3(int index, List<Integer> points) {
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
        return false;
    }
}
