package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.enums.ItemTileType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param index is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard7(int index, List<Integer> points){
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
        return this.points;
    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param gameData is the game data
     * @param name is the name of the player
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(GameData gameData, String name) {
        ItemTile[][] libraryGrid = gameData.getPlayerDashboards().get(name).getLibrary().getGrid();
        Map<ItemTileType, Integer> itemTileTypeCounter = new HashMap<>();
        for (ItemTileType itemTileType : ItemTileType.values()) {
            if (itemTileType != ItemTileType.EMPTY) {
                itemTileTypeCounter.put(itemTileType, 0);
            }
        }
        for(int i = 0; i < libraryGrid.length; i++){
            for(int j = 0; j < libraryGrid[0].length; j++){
                ItemTileType currentItemTileType = libraryGrid[i][j].getItemTileType();
                ItemTileType nextItemTile = libraryGrid[i+1][j+1].getItemTileType();
                if(currentItemTileType == nextItemTile && currentItemTileType != ItemTileType.EMPTY){
                    itemTileTypeCounter.put(currentItemTileType, itemTileTypeCounter.get(currentItemTileType) + 1);
                    return true;
                }
            }
        }
        return false;
    }
}
