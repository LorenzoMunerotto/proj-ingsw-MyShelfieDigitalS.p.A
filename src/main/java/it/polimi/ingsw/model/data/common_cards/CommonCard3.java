package it.polimi.ingsw.model.data.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.javatuples.Pair;

import java.util.ArrayList;
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
     * Check if the rules of the card are respected.
     *
     * @param gameData is the game data
     * @param name     is the name of the player
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(GameData gameData, String name) {
        ItemTile[][] libraryGrid = gameData.getPlayerDashboards().get(name).getLibrary().getGrid();
        List<Pair<Integer, Integer>> usedItemTiles = new ArrayList<>();
        int counter = 0;

        for (int row = libraryGrid.length - 1; row >= 0; row--) {
            for (int col = 0; col < libraryGrid[row].length; col++) {
                ItemTileType currentItemTileType = libraryGrid[row][col].getItemTileType();
                Pair<Integer, Integer> currentItemTile = new Pair<>(row, col);
                if (currentItemTileType == ItemTileType.EMPTY || usedItemTiles.contains(currentItemTile)) {
                    continue;
                }
                if (col + 3 < libraryGrid[row].length
                        && libraryGrid[row][col + 1].getItemTileType() == currentItemTileType
                        && libraryGrid[row][col + 2].getItemTileType() == currentItemTileType
                        && libraryGrid[row][col + 3].getItemTileType() == currentItemTileType) {
                    counter++;
                    usedItemTiles.add(currentItemTile);
                    usedItemTiles.add(new Pair<>(row, col + 1));
                    usedItemTiles.add(new Pair<>(row, col + 2));
                    usedItemTiles.add(new Pair<>(row, col + 3));
                    col += 3;
                } else if (row - 3 >= 0
                        && libraryGrid[row - 1][col].getItemTileType() == currentItemTileType
                        && libraryGrid[row - 2][col].getItemTileType() == currentItemTileType
                        && libraryGrid[row - 3][col].getItemTileType() == currentItemTileType) {
                    counter++;
                    usedItemTiles.add(currentItemTile);
                    usedItemTiles.add(new Pair<>(row - 1, col));
                    usedItemTiles.add(new Pair<>(row - 2, col));
                    usedItemTiles.add(new Pair<>(row - 3, col));
                } else if (col + 1 < libraryGrid[row].length && row - 1 >= 0
                        && libraryGrid[row - 1][col].getItemTileType() == currentItemTileType
                        && libraryGrid[row - 1][col + 1].getItemTileType() == currentItemTileType
                        && libraryGrid[row][col + 1].getItemTileType() == currentItemTileType) {
                    counter++;
                    usedItemTiles.add(currentItemTile);
                    usedItemTiles.add(new Pair<>(row - 1, col));
                    usedItemTiles.add(new Pair<>(row - 1, col + 1));
                    usedItemTiles.add(new Pair<>(row, col + 1));
                    col++;
                }
                if (counter >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkRules(Library library) {

        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = library.getListGroupsAdjacentTiles();
        int counter =0;
        for (Pair<ItemTileType, Integer> group : listGroupsAdjacentTiles){
            if(group.getValue0()!=ItemTileType.EMPTY && group.getValue1()==4){
                counter++;
            }
        }
        return counter>=4;

    }

}
