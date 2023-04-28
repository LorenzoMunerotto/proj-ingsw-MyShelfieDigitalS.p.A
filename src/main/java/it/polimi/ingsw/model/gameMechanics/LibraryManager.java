package it.polimi.ingsw.model.gameMechanics;


import it.polimi.ingsw.AbstractListenable;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;


import java.util.*;

/**
 * Class that manage the library
 */
public class LibraryManager extends AbstractListenable {
    /**
     * The library of the game.
     */
    private Library library;

    /**
     * Constructor of the class.
     *
     * @param library the library of the game
     */
    public LibraryManager(Library library) {
        this.library = library;
    }

    /**
     * Empty constructor of the class, for the controller.
     */
    public LibraryManager(){
    }

    /**
     * Setter of the library.
     *
     * @param library the library of the game
     */
    public void setLibrary(Library library) {
        this.library = library;
    }

    /**
     * This method inserts on the library the tiles chosen form the player.
     *
     * @param column  number of the column chosen by the player
     * @param itemTileList The orderly list of the tiles chosen by the player
     */
    public void insertItemTiles(int column, List<ItemTile> itemTileList)  {
        for(ItemTile itemTile : itemTileList){
            library.insertItemTile(column, itemTile);
        }
    }

    /**
     * This method trows an exception if in the column <i>col</i> there aren't
     * at least <i>num</i> cell EMPTY.
     *
     * @param col number of the column
     * @param num number of the cell EMPTY
     */
    public void hasEnoughSpace(Integer col, Integer num) throws BreakRulesException {

        int counter = 0;
        for (int row= library.getROWS()-1 ; row>=0; row--) {
            if (library.getItemTile(row,col).getItemTileType() == ItemTileType.EMPTY) counter++;
        }
        if(counter < num) throw new BreakRulesException(BreakRules.COLUMN_OUT_OF_SPACE);
    }

    /**
     * This method find groups of Adjacent item tiles of the same type on the library.
     * This method can be used to calculate points from the groups of Adjacent tiles and for check CommonCard 1 and 3.
     *
     * @return List of pair (ItemTileType, numberOfTile)
     */
    public List<Pair<ItemTileType, Integer>> getListGroupsAdjacentTiles() {
        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = new ArrayList<>();
        boolean[][] visited = new boolean[library.getROWS()][library.getCOLUMNS()];

        for (int i = 0; i < library.getROWS(); i++) {
            for (int j = 0; j < library.getCOLUMNS(); j++) {
                if (!visited[i][j]) {
                    ItemTileType currentItemTileType = library.getItemTile(i, j).getItemTileType();
                    if (currentItemTileType != ItemTileType.EMPTY) {
                        int count = deepSearch(i, j, visited, currentItemTileType);
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
     * @param row is the row of the current item tile
     * @param col is the column of the current item tile
     * @param visited is a matrix that contains the information about the visited item tiles
     * @param currentItemTileType is the type of the current item tile
     * @return the number of the item tiles of the same type of the current item tile
     */
    private int deepSearch(int row, int col, boolean[][] visited, ItemTileType currentItemTileType) {
        if (row < 0 || row >= library.getROWS() || col < 0 || col >= library.getCOLUMNS() || visited[row][col] || library.getItemTile(row, col).getItemTileType() != currentItemTileType) {
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
    public boolean isFull(){
        for (int row = 0; row < library.getROWS(); row++) {
            for (int col = 0; col < library.getCOLUMNS(); col++) {
                if (library.getItemTile(row, col).getItemTileType()==ItemTileType.EMPTY){
                    return false;
                }
            }
        }
        return true;
    }
}
