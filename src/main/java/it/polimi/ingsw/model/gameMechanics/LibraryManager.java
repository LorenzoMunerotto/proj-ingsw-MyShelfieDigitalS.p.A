package it.polimi.ingsw.model.gameMechanics;


import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that manage the library
 */
public class LibraryManager {
    /**
     * The library of the game.
     */
    private final Library library;

    /**
     * Constructor of the class.
     *
     * @param library the library of the game
     */
    public LibraryManager(Library library) {
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
    public void hasEnoughSpace(Integer col, Integer num){

        int counter = 0;
        for (int row= library.getROWS()-1 ; row>=0; row--) {
            if (library.getItemTile(row,col).getItemTileType() == ItemTileType.EMPTY) counter++;
        }
        if(counter < num) throw new IllegalArgumentException("The column cannot contain all the selected tiles");

    }

    /**
     * This method find groups of Adjacent item tiles of the same type on the library.
     * This method can be used to calculate points from the groups of Adjacent tiles and for check CommonCard 1 and 3.
     *
     * @return List of pair (ItemTileType, numberOfTile)
     */
    public List<Pair<ItemTileType, Integer>> getListGroupsAdjacentTiles(){
        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = new ArrayList<>();

        Character[][] helpGrid = new Character[library.getROWS()][library.getCOLUMNS()];
        for (int i = 0; i < library.getROWS(); i++) {
            Arrays.fill(helpGrid[i], 'W');
        }
        List<Pair<Integer, Integer>> sameTileList = new ArrayList<>();
        List<Pair<Integer, Integer>> differentTileTypeTail = new ArrayList<>();
        List<Pair<Integer, Integer>> sameTileTypeTail = new ArrayList<>();
        differentTileTypeTail.add(new Pair<>(0,0));

        while(differentTileTypeTail.size()!=0) {

            Integer row1;
            Integer col1;
            Pair<Integer, Integer> start1;
            do {
                start1 = differentTileTypeTail.remove(0);
                row1 = start1.getValue0();
                col1 = start1.getValue1();
            }while(helpGrid[row1][col1]=='B' && differentTileTypeTail.size()>0);

            ItemTileType currentItemTileType = library.getItemTile(row1, col1).getItemTileType();
            Integer counter = 1;
            boolean ok = differentTileTypeTail.size() != 0 || helpGrid[row1][col1] != 'B';
            if(helpGrid[row1][col1]!='B') {
                sameTileTypeTail.add(start1);
            }

            while (sameTileTypeTail.size() != 0) {

                Pair<Integer, Integer> start = sameTileTypeTail.remove(0);
                Integer row = start.getValue0();
                Integer col = start.getValue1();
                sameTileList.add(start);

                helpGrid[row][col]='G';

                if (Library.hasLowerItemTile(row, col) && helpGrid[row + 1][col] != 'B'&& helpGrid[row + 1][col] != 'P') {

                    if (library.getLowerItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row + 1, col));
                        counter++;
                        helpGrid[row + 1][col] = 'P';

                    } else {
                        if (helpGrid[row + 1][col] == 'W') {
                            helpGrid[row + 1][col] = 'G';
                            differentTileTypeTail.add(new Pair<>(row + 1, col));
                        }
                    }
                }
                if (Library.hasUpperItemTile(row, col) && helpGrid[row - 1][col] != 'B' && helpGrid[row - 1][col] != 'P') {

                    if (library.getUpperItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row - 1, col));
                        counter++;
                        helpGrid[row - 1][col] = 'P';

                    } else {
                        if (helpGrid[row - 1][col] == 'W') {
                            helpGrid[row - 1][col] = 'G';
                            differentTileTypeTail.add(new Pair<>(row - 1, col));
                        }
                    }
                }
                if (Library.hasRightItemTile(row, col) && helpGrid[row][col + 1] != 'B' && helpGrid[row][col + 1] != 'P') {

                    if (library.getRightItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row, col + 1));
                        counter++;
                        helpGrid[row][col + 1] = 'P';

                    } else {
                        if (helpGrid[row][col + 1] == 'W') {
                            helpGrid[row][col + 1] = 'G';
                            differentTileTypeTail.add(new Pair<>(row, col + 1));
                        }

                    }
                }
                if (Library.hasLeftItemTile(row, col) && helpGrid[row][col - 1] != 'B' && helpGrid[row][col - 1] != 'P') {

                    if (library.getLeftItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row, col - 1));
                        counter++;
                        helpGrid[row][col - 1] = 'P';
                    } else {
                        if (helpGrid[row][col - 1] == 'W') {
                            helpGrid[row][col - 1] = 'G';
                            differentTileTypeTail.add(new Pair<>(row, col - 1));
                        }
                    }
                }
                helpGrid[row][col] = 'B';

            }
            if(ok) {
                listGroupsAdjacentTiles.add(new Pair<>(currentItemTileType, counter));
            }
        }
        return listGroupsAdjacentTiles;
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
