package it.polimi.ingsw.model.gameMechanics;


import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;


import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


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
     * it calculates the numbers of point related to adjacent card melds for the specific library
     * @return total Points
     */
    public int adjacentPoints(){

        Predicate<Pair<ItemTileType,Integer>> filterGroup =
                (group) -> (group.getValue0()!=ItemTileType.EMPTY && group.getValue1()>2);


        Function<Pair<ItemTileType,Integer>,Integer> calculateCommonPoints =
                (group)->{
                    Integer numberOfTile = group.getValue1();

                    if (numberOfTile==3) return 2;
                    else if (numberOfTile==4) return 3;
                    else if (numberOfTile==5) return 5;
                    else return 8;

                };

        /* In caso di errori nei test per facilitare il debug
        System.out.println(getListGroupsAdjacentTiles().stream().filter(filterGroup).collect(Collectors.toList()));
        System.out.println(getListGroupsAdjacentTiles().stream().filter(filterGroup).map(calculateCommonPoints).collect(Collectors.toList()));
         */

        int totAdjacentPoint = library.getListGroupsAdjacentTiles().stream().filter(filterGroup).map(calculateCommonPoints).reduce(0, Integer::sum);
        return totAdjacentPoint;

    }

    public void hasEnoughSpace(Integer col, Integer num){

        int counter = 0;
        for (int row= library.getROWS()-1 ; row>=0; row--) {
            if (library.getItemTile(row,col).getItemTileType() == ItemTileType.EMPTY) counter++;
        }
        if(counter < num) throw new IllegalArgumentException("the column cannot contain all the selected tiles");

    }




}
