package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.List;

/**
 * Class representing the common goal card 7.
 */
public class CommonCard7 implements CommonGoalCard {

    /**
     * The index of the card.
     */
    private final int index;
    /**
     * The list of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 7, initializes index and points.
     *
     * @param index  is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard7(int index, List<Integer> points) {
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

        for (int row = 0; row <=1; row++) {
            int col = 0;
            ItemTile currentItemTile = library.getItemTile(row,col);
            if (currentItemTile.getItemTileType() == ItemTileType.EMPTY) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+1,col+1).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+2,col+2).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+3,col+3).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+4,col+4).getItemTileType()) {
                continue;
            }
            return true;
        }
        for (int row = 0; row <= 1; row++) {
            int col = 4;
            ItemTile currentItemTile = library.getItemTile(row,col);
            if (currentItemTile.getItemTileType() == ItemTileType.EMPTY) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+1,col-1).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+2,col-2).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+3,col-3).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+4,col-4).getItemTileType()) {
                continue;
            }
            return true;
        }
        return false;
    }
}
