package it.polimi.ingsw.model.gameEntity.personal_cards;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class Goal {

    private int row;
    private int column;
    private  String itemTileType;

    public Goal(int row, int column, String itemTileType){
        this.row = row;
        this.column = column;
        this.itemTileType = itemTileType;
    }

    public Goal(){

    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getItemTileType() {
        return itemTileType;
    }

    public void setItemTileType(String itemTileType) {
        this.itemTileType =itemTileType;
    }

    /*
    @Override
    public String toString() {
        return "<"+row+","+column+">"+" : "+itemTileType+" ";
    }

     */
}
