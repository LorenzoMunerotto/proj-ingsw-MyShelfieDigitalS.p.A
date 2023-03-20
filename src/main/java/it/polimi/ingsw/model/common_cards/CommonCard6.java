package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.enums.ItemTileType;

import java.util.HashMap;
import java.util.Map;

/**
 * A specific type of card that has a unique rule.
 */
public class CommonCard6 implements CommonGoalCard {

    /**
     * The index of the card in the deck.
     */
    private final int index = 6;

    /**
     * Checks if the rules of the card are respected.
     *
     * @param gameData contains the data of the game
     * @param name is the name of the player
     * @return true if the rules are respected
     */
    @Override
    public boolean checkRules(GameData gameData, String name) {
        /*
        ItemTile[][] libraryGrid = gameData.getPlayerDashboards().get(name).getLibrary().getGrid();
        Map<ItemTileType, Integer> itemTileTypeCounter = new HashMap<>();
        for(ItemTileType itemTileType: ItemTileType.values()){
            if (itemTileType != ItemTileType.EMPTY) {
                itemTileTypeCounter.put(itemTileType, 0);
            }
        }
        for (ItemTile[] row : libraryGrid) {
            for (ItemTile tile: row) {
                if (tile.getItemTileType() != ItemTileType.EMPTY) {
                    itemTileTypeCounter.put(tile.getItemTileType(), itemTileTypeCounter.get(tile.getItemTileType()) + 1);
                    if(itemTileTypeCounter.get(tile.getItemTileType()) > 8){
                        return true;
                    }
                }
            }
        } */
        return false;
    }
}
