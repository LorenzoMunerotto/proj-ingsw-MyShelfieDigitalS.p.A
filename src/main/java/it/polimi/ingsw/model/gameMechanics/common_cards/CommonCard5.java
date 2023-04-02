package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * @param library is the library grid
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {
        int counter = 0;

        for (int col = 0; col < library.getCOLUMNS(); col++) {
            Set<ItemTileType> uniqueItemTileTypes = new HashSet<>();
            int validItemTiles = 0;
            for (int row = 0; row < library.getROWS(); row++) {
                ItemTileType currentItemTileType = library.getItemTile(row,col).getItemTileType();
                if (currentItemTileType != ItemTileType.EMPTY) {
                    uniqueItemTileTypes.add(currentItemTileType);
                    validItemTiles++;
                }
            }
            if (validItemTiles == 6 && uniqueItemTileTypes.size() >= 1 && uniqueItemTileTypes.size() <= 3) {
                counter++;
                if (counter == 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
