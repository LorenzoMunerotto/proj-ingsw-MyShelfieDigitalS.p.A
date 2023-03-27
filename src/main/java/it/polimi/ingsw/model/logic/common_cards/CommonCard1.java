package it.polimi.ingsw.model.logic.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.javatuples.Pair;

import java.util.ArrayList;
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
     * @param libraryGrid is the library grid
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(ItemTile[][] libraryGrid) {
        List<Pair<Integer, Integer>> usedItemTiles = new ArrayList<>();
        int counter = 0;

        for (int row = libraryGrid.length - 1; row >= 0; row--) {
            for (int col = 0; col < libraryGrid[row].length; col++) {
                ItemTileType currentItemTileType = libraryGrid[row][col].getItemTileType();
                Pair<Integer, Integer> currentItemTile = new Pair<>(row, col);
                if (currentItemTileType == ItemTileType.EMPTY || usedItemTiles.contains(currentItemTile)) {
                    continue;
                }
                if (col + 1 < libraryGrid[row].length && libraryGrid[row][col + 1].getItemTileType() == currentItemTileType) {
                    counter++;
                    usedItemTiles.add(currentItemTile);
                    usedItemTiles.add(new Pair<>(row, col + 1));
                    col++;
                } else if (row - 1 >= 0 && libraryGrid[row - 1][col].getItemTileType() == currentItemTileType) {
                    counter++;
                    usedItemTiles.add(currentItemTile);
                    usedItemTiles.add(new Pair<>(row - 1, col));
                }
                if (counter >= 6) {
                    return true;
                }
            }
        }
        return false;
    }
}
