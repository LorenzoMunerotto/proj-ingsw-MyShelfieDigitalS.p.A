package it.polimi.ingsw.model.gameMechanics;


import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Class that manage the library
 */
public class LibraryManager {

    private final Library library;

    public LibraryManager(Library library) {
        this.library = library;
    }

    /**
     * insert on the library the tiles chosen form the 
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
     * at least <i>num</i> cell EMPTY
     * @param col
     * @param num
     */
    public void hasEnoughSpace(Integer col, Integer num){

        int counter = 0;
        for (int row= library.getROWS()-1 ; row>=0; row--) {
            if (library.getItemTile(row,col).getItemTileType() == ItemTileType.EMPTY) counter++;
        }
        if(counter < num) throw new IllegalArgumentException("the column cannot contain all the selected tiles");

    }

    /**
     * <p>This method find groups of Adjacent item tiles of the same type on the library <br>
     * This method can be used for calculate points from the groups of Adjacent tiles and for check CommonCards3 and CC1 <br>
     * </p>
     *
     * @return List of pair (ItemTileType, numberOfTile)
     *
     */
    public List<Pair<ItemTileType, Integer>> getListGroupsAdjacentTiles(){
        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = new ArrayList<>();

        Character[][] helpgrid = new Character[Library.getROWS()][Library.getCOLUMNS()];
        for (int i = 0; i < Library.getROWS(); i++) {
            Arrays.fill(helpgrid[i], Character.valueOf('W'));
        }
        List<Pair<Integer, Integer>> sameTileList = new ArrayList<>();
        List<Pair<Integer, Integer>> differentTileTypeTail = new ArrayList<>();
        List<Pair<Integer, Integer>> sameTileTypeTail = new ArrayList<>();
        differentTileTypeTail.add(new Pair<>(0,0));

        while(differentTileTypeTail.size()!=0) {

            Integer row1;
            Integer col1;
            Pair start1;
            do {
                start1 = differentTileTypeTail.remove(0);
                row1 = (Integer) start1.getValue0();
                col1 = (Integer) start1.getValue1();
            }while(helpgrid[row1][col1]=='B' && differentTileTypeTail.size()>0);

            ItemTileType currentItemTileType = library.getItemTile(row1, col1).getItemTileType();
            Integer counter = 1;
            Boolean ok = true;
            if (differentTileTypeTail.size()==0 && helpgrid[row1][col1]=='B'){
                ok=false;
            }
            if(helpgrid[row1][col1]!='B') {

                sameTileTypeTail.add(start1);

            }

            while (sameTileTypeTail.size() != 0) {


                Pair start = sameTileTypeTail.remove(0);
                Integer row = (Integer) start.getValue0();
                Integer col = (Integer) start.getValue1();
                sameTileList.add(start);

                helpgrid[row][col]='G';

                if (Library.hasLowerItemTile(row, col) && helpgrid[row + 1][col] != 'B'&& helpgrid[row + 1][col] != 'P') {

                    if (library.getLowerItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row + 1, col));
                        counter++;
                        helpgrid[row + 1][col] = 'P';

                    } else {
                        if (helpgrid[row + 1][col] == 'W') {
                            helpgrid[row + 1][col] = 'G';
                            differentTileTypeTail.add(new Pair<>(row + 1, col));
                        }

                    }
                }
                if (Library.hasUpperItemTile(row, col) && helpgrid[row - 1][col] != 'B' && helpgrid[row - 1][col] != 'P') {

                    if (library.getUpperItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row - 1, col));
                        counter++;
                        helpgrid[row - 1][col] = 'P';

                    } else {
                        if (helpgrid[row - 1][col] == 'W') {
                            helpgrid[row - 1][col] = 'G';
                            differentTileTypeTail.add(new Pair<>(row - 1, col));
                        }
                    }
                }
                if (Library.hasRightItemTile(row, col) && helpgrid[row][col + 1] != 'B' && helpgrid[row][col + 1] != 'P') {

                    if (library.getRightItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row, col + 1));
                        counter++;
                        helpgrid[row][col + 1] = 'P';

                    } else {
                        if (helpgrid[row][col + 1] == 'W') {
                            helpgrid[row][col + 1] = 'G';
                            differentTileTypeTail.add(new Pair<>(row, col + 1));
                        }

                    }
                }
                if (Library.hasLeftItemTile(row, col) && helpgrid[row][col - 1] != 'B' && helpgrid[row][col - 1] != 'P') {

                    if (library.getLeftItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row, col - 1));
                        counter++;
                        helpgrid[row][col - 1] = 'P';
                    } else {
                        if (helpgrid[row][col - 1] == 'W') {
                            helpgrid[row][col - 1] = 'G';
                            differentTileTypeTail.add(new Pair<>(row, col - 1));
                        }

                    }
                }
                helpgrid[row][col] = 'B';


            }
            if(ok) {

                listGroupsAdjacentTiles.add(new Pair<>(currentItemTileType, counter));

            }

        }

        return listGroupsAdjacentTiles;
    }

    /**
     * Tell if the library is full
     * @return true if library has no ItemTile with type==EMPTY, false otherwise
     */
    public boolean isFull(){
        for (int row = 0; row < Library.getROWS(); row++) {
            for (int col = 0; col < Library.getCOLUMNS(); col++) {
                if (library.getItemTile(row, col).getItemTileType()==ItemTileType.EMPTY){
                    return false;
                }
            }
        }
        return true;
    }



}
