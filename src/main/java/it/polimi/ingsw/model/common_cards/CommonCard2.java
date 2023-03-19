package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.enums.ItemTileType;

/**
 * A specific type of card that has a unique rule.
 */
public class CommonCard2 implements CommonGoalCard{

    /**
     * The index of the card in the deck.
     */
    private final int index = 2;

    /**
     * Checks if the rules of the card are respected.
     *
     * @param gameData contains the data of the game
     * @param name is the name of the player
     * @return true if the rules are respected
     */
    @Override
    public boolean checkRules(GameData gameData, String name) {
        ItemTile[][] libraryGrid = gameData.getPlayerDashboards().get(name).getLibrary().getGrid();
        ItemTileType itemTile1 = libraryGrid[0][0].getItemTileType();
        ItemTileType itemTile2 = libraryGrid[0][4].getItemTileType();
        ItemTileType itemTile3 = libraryGrid[5][0].getItemTileType();
        ItemTileType itemTile4 = libraryGrid[5][4].getItemTileType();
        return itemTile1 == itemTile2 && itemTile2 == itemTile3 && itemTile3 == itemTile4;
    }
}
