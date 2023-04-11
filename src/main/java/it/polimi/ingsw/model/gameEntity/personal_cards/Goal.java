package it.polimi.ingsw.model.gameEntity.personal_cards;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return row == goal.row && column == goal.column && itemTileType.equals(goal.itemTileType);
    }


}
