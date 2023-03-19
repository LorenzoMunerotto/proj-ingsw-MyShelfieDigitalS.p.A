package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;

/**
 * A card that can be used during a game.
 * Each card has a unique pattern that can affect the points of a player.
 */
public interface CommonGoalCard {

    /**
     * Checks if the rules of the card are respected.
     *
     * @param gameData contains the data of the game
     * @param name is the name of the player
     * @return true if the rules are respected
     */
    boolean checkRules(GameData gameData, String name);
}
