package it.polimi.ingsw.model.enums;

/**
 * Enumeration of the different types of the item tiles.
 */
public enum ItemTileType {
    CATS("GREEN"),
    BOOKS("WHITE"),
    GAMES("YELLOW"),
    FRAMES("BLUE"),
    TROPHIES("LIGHT_BLUE"),
    PLANTS("PINK"),
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
