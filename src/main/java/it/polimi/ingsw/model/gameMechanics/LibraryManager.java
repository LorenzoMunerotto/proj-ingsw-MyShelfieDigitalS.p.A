package it.polimi.ingsw.model.gameMechanics;


import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

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
    public void insertItemTiles(int column, List<ItemTile> itemTileList){

        int counter = 0;
        for (int row= library.getROWS()-1 ; row<=0; row--) {
            if (library.getItemTile(row,column).getItemTileType() == ItemTileType.EMPTY) counter++;
        }
        if(counter > itemTileList.size()) throw new IllegalArgumentException("The column is already full");

    }

    public List<Pair<ItemTileType, Integer>> getListGroupsAdjacentTiles(){
        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = new ArrayList<>();

        Character[][] helpgrid = new Character[library.getROWS()][library.getCOLUMNS()];
        for (int i = 0; i < library.getROWS(); i++) {
            for (int j = 0; j < library.getCOLUMNS(); j++) {
                helpgrid[i][j] = Character.valueOf('W');
            }
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

                if (library.hasLowerItemTile(row, col) && helpgrid[row + 1][col] != 'B'&& helpgrid[row + 1][col] != 'P') {

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
                if (library.hasUpperItemTile(row, col) && helpgrid[row - 1][col] != 'B' && helpgrid[row - 1][col] != 'P') {

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
                if (library.hasRightItemTile(row, col) && helpgrid[row][col + 1] != 'B' && helpgrid[row][col + 1] != 'P') {

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
                if (library.hasLeftItemTile(row, col) && helpgrid[row][col - 1] != 'B' && helpgrid[row][col - 1] != 'P') {

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
}
