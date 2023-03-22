package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.enums.ItemTileType;

import java.util.List;

/**
 * Class representing the common goal card 11.
 */
public class CommonCard11 implements CommonGoalCard {

    /**
     * The index of the card.
     */
    private final int index;
    /**
     * The list of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 11, initializes index and points.
     *
     * @param index  is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard11(int index, List<Integer> points) {
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
     * Check if the rules of the card are respected.
     *
     * @param gameData is the game data
     * @param name     is the name of the player
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(GameData gameData, String name) {
        ItemTile[][] libraryGrid = gameData.getPlayerDashboards().get(name).getLibrary().getGrid();
        for (int row = 0; row < libraryGrid.length - 2; row++) {
            for (int col = 0; col < libraryGrid[0].length - 2; col++) {
                ItemTileType currentType = libraryGrid[row][col].getItemTileType();
                if (currentType == ItemTileType.EMPTY) {
                    continue;
                }
                if (currentType != libraryGrid[row][col + 2].getItemTileType()) {
                    continue;
                }
                if (currentType != libraryGrid[row + 1][col + 1].getItemTileType()) {
                    continue;
                }
                if (currentType != libraryGrid[row + 2][col].getItemTileType()) {
                    continue;
                }
                if (currentType != libraryGrid[row + 2][col + 2].getItemTileType()) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
}
