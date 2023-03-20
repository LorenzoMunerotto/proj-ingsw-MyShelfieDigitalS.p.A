package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;

import java.util.List;

/**
 * Class representing the common goal card 5.
 */
public class CommonCard5 implements CommonGoalCard {

    /**
     * The index of the card.
     */
    private final int index;
/**
     * The list of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 5, initializes index and points.
     *
     * @param index is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard5(int index, List<Integer> points){
        this.index = index;
        this.points = points;
    }

    /**
     * Get the index of the card.
     *
     * @return the index of the card
     */
    @Override
    public int getIndex(){
        return this.index;
    }

    /**
     * Get the points on the card.
     *
     * @return the points on the card
     */
    @Override
    public List<Integer> getPoints() {
        return points;
    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param gameData is the game data
     * @param name is the name of the player
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(GameData gameData, String name) {
        return false;
    }
}
