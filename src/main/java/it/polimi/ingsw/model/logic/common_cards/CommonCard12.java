package it.polimi.ingsw.model.logic.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;

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
     * @param library is the Library
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {
        int counter = 0;

        for (int row=1; row< library.getROWS(); row++) {
            if (library.getItemTile(row-1,row-1).getItemTileType() != ItemTileType.EMPTY || library.getItemTile(row,row-1).getItemTileType() == ItemTileType.EMPTY) {
                break;
            }
            counter++;
            if (counter == 5) {
                return true;
            }
        }
        counter  = 0;
        for (int row = 1; row < library.getROWS(); row++) {
            if (library.getItemTile(row-1,5-row).getItemTileType() != ItemTileType.EMPTY || library.getItemTile(row,5-row).getItemTileType() == ItemTileType.EMPTY) {
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
