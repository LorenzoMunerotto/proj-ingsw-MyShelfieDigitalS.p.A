package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;

import java.util.List;

/**
 * Class representing the common goal card 1.
 */
public class CommonCard1 implements CommonGoalCard{

    /**
     * Index of the card.
     */
    private final int index;
/**
     * List of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 1, initializes index and points.
     *
     * @param index index of the card
     * @param points list of the points on the card
     */
    public CommonCard1(int index, List<Integer> points){
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
    public List<Integer> getPoints(){
        return this.points;
    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param gameData the game data
     * @param name the name of the player
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(GameData gameData, String name) {
        return false;
    }
}
