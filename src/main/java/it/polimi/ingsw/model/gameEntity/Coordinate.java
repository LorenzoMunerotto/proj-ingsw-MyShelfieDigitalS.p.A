package it.polimi.ingsw.model.gameEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents the couple (row,column) which identifies a cell in the board.
 */
public class Coordinate implements Serializable {

    /**
     * The number which identifies the row
     */
    private final int row;
    /**
     * The number which identifies the column
     */
    private final int column;

    /**
     * Default constructor.
     *
     * @param row    is the row
     * @param column is the column
     */
    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get the row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column.
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Compares this Coordinate with the specified object for equality.
     * Two Coordinates are considered equal if both their row and column values are the same.
     *
     * @param obj the object to be compared for equality with this Coordinate
     * @return true if the specified object is equal to this Coordinate, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate that = (Coordinate) obj;
        return row == that.row && column == that.column;
    }

    /**
     * Returns the hash code of the object.
     *
     * @return a hash code value for this Coordinate
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}