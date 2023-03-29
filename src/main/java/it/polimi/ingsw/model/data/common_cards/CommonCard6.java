package it.polimi.ingsw.model.data.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A specific type of card that has a unique rule.
 */
public class CommonCard6 implements CommonGoalCard {

    /**
     * The index of the card in the deck.
     */
    private final int index;
    /**
     * List of point tiles.
     */
    private final List<Integer> points;

    /**
     * Constructor for card6, initializing the index and the points according to the number of players.
     *
     * @param index is the index of the card in the deck
     * @param points list of points tile
     */
    public CommonCard6(int index, List<Integer> points) {
        this.index = index;
        this.points = points;
    }

    /**
     * Get the index of the card.
     *
     * @return index of the card
     */
    @Override
    public int getIndex() {
        return this.index;
    }

    /**
     * Get the list of point tiles.
     *
     * @return points tile
     */
    @Override
    public List<Integer> getPoints() {
        return this.points;
    }

    /**
     * Checks if the rules of the card are respected.
     *
     * @param gameData contains the data of the game
     * @param name     is the name of the player
     * @return true if the rules are respected
     */
    @Override
    public boolean checkRules(GameData gameData, String name) {
        ItemTile[][] libraryGrid = gameData.getPlayerDashboards().get(name).getLibrary().getGrid();
        Map<ItemTileType, Integer> itemTileTypeCounter = new HashMap<>();

        for (ItemTile[] itemTiles : libraryGrid) {
            for (int col = 0; col < libraryGrid[0].length; col++) {
                ItemTileType currentItemTileType = itemTiles[col].getItemTileType();
                if (currentItemTileType != ItemTileType.EMPTY) {
                    itemTileTypeCounter.put(currentItemTileType, itemTileTypeCounter.getOrDefault(currentItemTileType, 0) + 1);
                    if (itemTileTypeCounter.get(currentItemTileType) >= 8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkRules(Library library) {
        return false;
    }
}
