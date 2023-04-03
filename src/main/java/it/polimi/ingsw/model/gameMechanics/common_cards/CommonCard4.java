package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing the common goal card 4.
 */
public class CommonCard4 implements CommonGoalCard {

    /**
     * The index of the card.
     */
    private final int index;
/**
     * The list of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 4, initializes index and points.
     *
     * @param index is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard4(int index, List<Integer> points){
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
     * @param library is the Library grid
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {
        Map<ItemTileType, Integer> itemTileTypeCounter = new HashMap<>();
        List<Pair<Integer, Integer>> usedTiles = new ArrayList<>();

        for (int row = library.getROWS()- 2; row >= 0; row--) {
            for (int col = 0; col < library.getCOLUMNS() - 1; col++) {
                ItemTileType currentItemTileType = library.getItemTile(row,col).getItemTileType();
                Pair<Integer, Integer> currentItemTile = new Pair<>(row, col);
                if(currentItemTileType == ItemTileType.EMPTY || usedTiles.contains(currentItemTile)){
                    continue;
                }

                    if(library.getItemTile(row+1,col).getItemTileType() == currentItemTileType
                            && library.getItemTile(row,col+1).getItemTileType() == currentItemTileType
                            && library.getItemTile(row+1,col+1).getItemTileType() == currentItemTileType) {
                    itemTileTypeCounter.put(currentItemTileType, itemTileTypeCounter.getOrDefault(currentItemTileType, 0) + 1);
                    if(itemTileTypeCounter.get(currentItemTileType) >= 2){
                        return true;
                    }
                    usedTiles.add(currentItemTile);
                    usedTiles.add(new Pair<>(row + 1, col));
                    usedTiles.add(new Pair<>(row, col + 1));
                    usedTiles.add(new Pair<>(row + 1, col + 1));
                    col++;
                }
            }
        }
        return false;
    }
}
