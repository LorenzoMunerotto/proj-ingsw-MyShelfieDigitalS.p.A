package it.polimi.ingsw.model.gameEntity.enums;

/**
 * Enumeration of the different types of the item tiles.
 */
public enum ItemTileType {
    CAT,
    BOOK,
    GAME,
    FRAME,
    TROPHY,
    PLANT,
    /**
     * Empty cell.
     */
    EMPTY,
    /**
     * Null cell.
     */
    NULL;

    /**
     * Returns the string representation of the item tile type.
     *
     * @return the string representation of the item tile type.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}