package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing a single goal of a common goal card.
 */
public class Goal {

    private final int row;
    private final int column;
    private final ItemTileType itemTileType;

    public Goal(@JsonProperty("row") int row, @JsonProperty("column") int column, @JsonProperty("itemTileType") ItemTileType itemTileType) {
        this.row = row;
        this.column = column;
        this.itemTileType = itemTileType;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ItemTileType getItemTileType() {
        return itemTileType;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "row=" + row +
                ", column=" + column +
                ", itemTileType='" + itemTileType + '\'' +
                '}';
    }
}
