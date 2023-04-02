package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class representing the common goal card 9.
 */
public class CommonCard9 implements CommonGoalCard {

    /**
     * The index of the card.
     */
    private final int index;
    /**
     * The list of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 9, initializes index and points.
     *
     * @param index  is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard9(int index, List<Integer> points) {
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

        firstLoop:
        for (int col=0; col< library.getCOLUMNS(); col++) {
            Set<ItemTileType> distinctTypes = new HashSet<>();

            for (int row=0; row< library.getROWS(); row++) {
                ItemTileType currentType = library.getItemTile(row,col).getItemTileType();
                if (currentType == ItemTileType.EMPTY) {
                    continue firstLoop;
                }
                distinctTypes.add(currentType);
            }
            if (distinctTypes.size() == library.getROWS()) {
                counter++;
            }
            if (counter == 2) {
                return true;
            }
        }
        return false;
    }
}
