package it.polimi.ingsw.model.data.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class representing the common goal card 8.
 */
public class CommonCard8 implements CommonGoalCard {

    /**
     * The index of the card.
     */
    private final int index;
    /**
     * The list of the points on the card.
     */
    private final List<Integer> points;

    /**
     * Constructor for common card 8, initializes index and points.
     *
     * @param index  is the index of the card
     * @param points is the list of the points on the card
     */
    public CommonCard8(int index, List<Integer> points) {
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

        for (ItemTile[] itemTiles : libraryGrid) {
            Set<ItemTileType> uniqueItemTileTypes = new HashSet<>();
            int validItemTiles = 0;
            for (ItemTile itemTile : itemTiles) {
                ItemTileType currentItemTileType = itemTile.getItemTileType();
                if (currentItemTileType != ItemTileType.EMPTY) {
                    uniqueItemTileTypes.add(currentItemTileType);
                    validItemTiles++;
                }
            }
            if (validItemTiles == 5 && uniqueItemTileTypes.size() >= 1 && uniqueItemTileTypes.size() <= 3) {
                counter++;
                if (counter == 4) {
                    return true;
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
