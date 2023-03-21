package it.polimi.ingsw.model.personal_card;

public class PersonalConstraint {

    private final int row;
    private final int column;
    private final String color;

    public PersonalConstraint(int row, int column, String color){
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
