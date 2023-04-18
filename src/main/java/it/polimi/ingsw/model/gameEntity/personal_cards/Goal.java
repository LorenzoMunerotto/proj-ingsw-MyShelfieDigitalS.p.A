package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing a single goal of a common goal card.
 */
public class Goal {
    /**
     * The row of the goal.
     */
    private final int row;
    /**
     * The column of the goal.
     */
    private final int column;
    /**
     * The type of the goal.
     */
    private final ItemTileType itemTileType;

    /**
     * Constructor of the class.
     *
     * @param row the row of the goal
     * @param column the column of the goal
     * @param itemTileType the type of the goal
     */
    public Goal(@JsonProperty("row") int row, @JsonProperty("column") int column, @JsonProperty("itemTileType") ItemTileType itemTileType) {
        this.row = row;
        this.column = column;
        this.itemTileType = itemTileType;
    }

    /**
     * Get the row of the goal.
     *
     * @return the row of the goal
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column of the goal.
     *
     * @return the column of the goal
     */
    public int getColumn() {
        return column;
    }

    /**
     * Get the type of the goal.
     *
     * @return the type of the goal
     */
    public ItemTileType getItemTileType() {
        return itemTileType;
    }

    /**
     * Get the string representation of the goal.
     *
     * @return the string representation of the goal
     */
    @Override
    public String toString() {
        return "Goal{" +
                "row=" + row +
                ", column=" + column +
                ", itemTileType='" + itemTileType + '\'' +
                '}';
    }
}
