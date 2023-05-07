package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.listener.AbstractListenable;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameState.events.LibraryUpdateEvent;
import org.javatuples.Pair;

import java.util.*;
import java.util.zip.CRC32;

/**
 * Class that manage the library
 */
public class LibraryManager extends AbstractListenable {
    /**
     * The library of the game.
     */
    private Library library;
    /**
     * The username of the player that owns the library.
     */
    private String username;

    /**
     * Constructor of the class.
     */
    public LibraryManager() {
    }

    /**
     * Get the library.
     *
     * @return the library of the current player
     */
    public Library getLibrary() {
        return this.library;
    }

    /**
     * Get the username.
     *
     * @return the username of the current player
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Set the library.
     *
     * @param library the library of the game
     */
    public void setLibrary(Library library) {
        this.library = library;
    }

    /**
     * Set the username.
     *
     * @param username the username of the player
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method inserts on the library the tiles chosen form the player.
     *
     * @param column       number of the column chosen by the player
     * @param itemTileList The orderly list of the tiles chosen by the player
     */
    public void insertItemTiles(int column, List<ItemTileType> itemTileList) {
        for (ItemTileType itemTile : itemTileList) {
            for (int row = library.getLibraryGrid().length - 1; row >= 0; row--) {
                if (library.getItemTile(row, column) == ItemTileType.EMPTY) {
                    library.setItemTile(row, column, itemTile);
                    break;
                }
            }
        }
        notifyAllListeners(new LibraryUpdateEvent(itemTileList, column, username, calculateCRC()));
    }

    /**
     * This method returns the number of empty tiles in the column.
     *
     * @param column the column of the library
     * @return the number of empty tiles in the column
     */
    public int emptyTilesCounter(int column) {
        int counter = 0;
        for (int row = library.getLibraryGrid().length - 1; row >= 0; row--) {
            if (library.getItemTile(row, column) == ItemTileType.EMPTY) counter++;
        }
        return counter;
    }

    /**
     * This method find groups of Adjacent item tiles of the same type on the library.
     * This method can be used to calculate points from the groups of Adjacent tiles and for check CommonCard 1 and 3.
     *
     * @return List of pair (ItemTileType, numberOfTile)
     */
    public List<Pair<ItemTileType, Integer>> getListGroupsAdjacentTiles() {
        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = new ArrayList<>();
        boolean[][] visited = new boolean[library.getLibraryGrid().length][library.getLibraryGrid()[0].length];

        for (int row = 0; row < library.getLibraryGrid().length; row++) {
            for (int column = 0; column < library.getLibraryGrid()[0].length; column++) {
                if (!visited[row][column]) {
                    ItemTileType currentItemTileType = library.getItemTile(row, column);
                    if (currentItemTileType != ItemTileType.EMPTY) {
                        int count = deepSearch(row, column, visited, currentItemTileType);
                        listGroupsAdjacentTiles.add(new Pair<>(currentItemTileType, count));
                    }
                }
            }
        }
        return listGroupsAdjacentTiles;
    }

    /**
     * It is a recursive method that find the largest group of Adjacent item tiles of the same type on the library.
     *
     * @param row                 is the row of the current item tile
     * @param col                 is the column of the current item tile
     * @param visited             is a matrix that contains the information about the visited item tiles
     * @param currentItemTileType is the type of the current item tile
     * @return the number of the item tiles of the same type of the current item tile
     */
    private int deepSearch(int row, int col, boolean[][] visited, ItemTileType currentItemTileType) {
        if (row < 0 || row >= library.getLibraryGrid().length || col < 0 || col >= library.getLibraryGrid()[0].length || visited[row][col] || library.getItemTile(row, col) != currentItemTileType) {
            return 0;
        }

        visited[row][col] = true;
        int count = 1;

        count += deepSearch(row + 1, col, visited, currentItemTileType);
        count += deepSearch(row - 1, col, visited, currentItemTileType);
        count += deepSearch(row, col + 1, visited, currentItemTileType);
        count += deepSearch(row, col - 1, visited, currentItemTileType);

        return count;
    }

    /**
     * This method find the largest group of Adjacent item tiles of the same type on the library.
     *
     * @param listGroupsAdjacentTiles list of pair (ItemTileType, numberOfTile)
     * @return Map of pair (ItemTileType, numberOfTile) with the largest group of Adjacent item tiles of the same type on the library
     */
    public Map<ItemTileType, Integer> getLargestGroupsMap(List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles) {
        Map<ItemTileType, Integer> largestGroupsMap = new HashMap<>();
        for (Pair<ItemTileType, Integer> pair : listGroupsAdjacentTiles) {
            ItemTileType type = pair.getValue0();
            int size = pair.getValue1();
            if (!largestGroupsMap.containsKey(type) || size > largestGroupsMap.get(type)) {
                largestGroupsMap.put(type, size);
            }
        }
        return largestGroupsMap;
    }

    /**
     * Check if the library is full.
     *
     * @return true if library has no ItemTile with type==EMPTY, false otherwise
     */
    public boolean isFull() {
        for (int row = 0; row < library.getLibraryGrid().length; row++) {
            for (int col = 0; col < library.getLibraryGrid()[0].length; col++) {
                if (library.getItemTile(row, col) == ItemTileType.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public long calculateCRC(){
        CRC32 checksum = new CRC32();
        for (int row = 0; row < library.getLibraryGrid().length; row++) {
            for (int col = 0; col < library.getLibraryGrid()[0].length; col++) {
                checksum.update(library.getItemTile(row, col).toString().getBytes());
            }
        }
        return checksum.getValue();
    }
}
