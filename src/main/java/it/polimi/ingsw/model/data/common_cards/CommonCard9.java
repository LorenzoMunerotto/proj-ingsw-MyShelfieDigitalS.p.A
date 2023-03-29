package it.polimi.ingsw.model.data.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;

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

        firstLoop:
        for(int col = 0; col < libraryGrid[0].length; col++){
            Set<ItemTileType> distinctTypes = new HashSet<>();

            for (ItemTile[] itemTiles : libraryGrid) {
                ItemTileType currentType = itemTiles[col].getItemTileType();
                if (currentType == ItemTileType.EMPTY) {
                    continue firstLoop;
                }
                distinctTypes.add(currentType);
            }
            if(distinctTypes.size() == libraryGrid.length){
                counter++;
            }
            if(counter == 2){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkRules(Library library) {
        return false;
    }
}
