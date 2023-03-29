package it.polimi.ingsw.model.data.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;

import java.util.List;

/**
 * Class representing the common goal card 1.
 */
public class CommonCard2 implements CommonGoalCard{

    /**
     * The index of the card.
     */
    private final int index;
    /**
     * The list of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 1, initializes index and points.
     *
     * @param index is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard2(int index, List<Integer> points){
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
        return this.points;
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
        ItemTile[][] libraryGrid = gameData.getPlayerDashboards().get(name).getLibrary().getGrid();

        ItemTileType itemTile1 = libraryGrid[0][0].getItemTileType();
        ItemTileType itemTile2 = libraryGrid[0][4].getItemTileType();
        ItemTileType itemTile3 = libraryGrid[5][0].getItemTileType();
        ItemTileType itemTile4 = libraryGrid[5][4].getItemTileType();
        return itemTile1 == itemTile2 && itemTile2 == itemTile3 && itemTile3 == itemTile4;
    }

    @Override
    public boolean checkRules(Library library) {
        return false;
    }
}
