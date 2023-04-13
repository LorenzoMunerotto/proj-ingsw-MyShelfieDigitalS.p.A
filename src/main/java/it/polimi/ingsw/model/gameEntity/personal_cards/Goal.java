package it.polimi.ingsw.model.gameEntity.personal_cards;

/**
 * Class representing a single goal of a common goal card.
 */
public class Goal {
    /**
     * Row of the goal.
     */
    private int row;
    /**
     * Column of the goal.
     */
    private int column;
    /**
     * Type of the goal.
     */
    private  String itemTileType;

    /**
     * Constructor of the class.
     *
     * @param row Row of the goal.
     * @param column Column of the goal.
     * @param itemTileType Type of the goal.
     */
    public Goal(int row, int column, String itemTileType){
        this.row = row;
        this.column = column;
        this.itemTileType = itemTileType;
    }

    /**
     * Get the row of the goal.
     *
     * @return Row of the goal.
     */
    public int getRow() {
        return row;
    }

    /**
     * Set the row of the goal.
     *
     * @param row Row of the goal.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Get the column of the goal.
     *
     * @return Column of the goal.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Set the column of the goal.
     *
     * @param column Column of the goal.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Get the type of the goal.
     *
     * @return Type of the goal.
     */
    public String getItemTileType() {
        return itemTileType;
    }

    /**
     * Set the type of the goal.
     *
     * @param itemTileType Type of the goal.
     */
    public void setItemTileType(String itemTileType) {
        this.itemTileType =itemTileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return row == goal.row && column == goal.column && itemTileType.equals(goal.itemTileType);
    }
}
