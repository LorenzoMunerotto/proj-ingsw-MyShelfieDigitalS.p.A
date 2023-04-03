package it.polimi.ingsw.model.gameMechanics.personal_cards;

public class Goal {

    private final int row;
    private final int column;
    private final String color;

    public Goal(int row, int column, String color){
        this.row = row;
        this.column = column;
        this.color = color;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public String getColor(){
        return this.color;
    }
}
