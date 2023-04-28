package it.polimi.ingsw.model.gameEntity;

import java.io.Serializable;

/**
 * This class represents the couple (row,col) which identifies a boardCell.
 */
public class Coordinate implements Serializable {

    /**
     * the number which identifies the row
     */
    private final int row;
    /**
     * the number which identifies the column
     */
    private final int col;

    /**
     * Constructor of the Coordinate.
     *
     * @param row is the row
     * @param col is the column
     */
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Get the row.
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column.
     *
     * @return col
     */
    public int getCol() {
        return col;
    }
}
