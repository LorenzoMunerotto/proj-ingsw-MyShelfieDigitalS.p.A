package it.polimi.ingsw.model.gameEntity.enums;

/**
 * Enumeration of the different types of the item tiles.
 */
public enum ItemTileType {
    CAT("GREEN"),
    BOOK("WHITE"),
    GAME("YELLOW"),
    FRAME("BLUE"),
    TROPHY("LIGHT_BLUE"),
    PLANT("PINK"),
    /**
     * No item tile.
     */
    EMPTY("NONE");

    /**
     * Color associated to each type of the item tile.
     */
    private final String color;

    /**
     * Constructor for ItemTileType, initializing the color for each type.
     *
     * @param color color associated to each type
     */
    ItemTileType(String color) {
        this.color = color;
    }

    /**
     * Get color of the item tile type.
     *
     * @return color of the item tile type
     */
    public String getColor() {
        return this.color;
    }



}
