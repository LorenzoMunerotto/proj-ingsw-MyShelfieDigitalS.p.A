package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.enums.ItemTileType;

import java.util.List;

/**
 * Class representing the common goal card 12.
 */
public class CommonCard12 implements CommonGoalCard {

    /**
     * The index of the card.
     */
    private final int index;
    /**
     * The list of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 12, initializes index and points.
     *
     * @param index  is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard12(int index, List<Integer> points) {
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
        int counter = 0;

        for (int row = 1; row < libraryGrid.length; row++) {
            if (libraryGrid[row - 1][row - 1].getItemTileType() != ItemTileType.EMPTY || libraryGrid[row][row - 1].getItemTileType() == ItemTileType.EMPTY) {
                break;
            }
            counter++;
            if (counter == 5) {
                return true;
            }
        }
        counter  = 0;
        for (int row = 1; row < libraryGrid.length; row++) {
            if (libraryGrid[row - 1][5 - row].getItemTileType() != ItemTileType.EMPTY || libraryGrid[row][5 - row].getItemTileType() == ItemTileType.EMPTY) {
                break;
            }
            counter++;
            if (counter == 5) {
                return true;
            }
        }
        return false;
    }
}
