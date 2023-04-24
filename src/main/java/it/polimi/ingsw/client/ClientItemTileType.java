package it.polimi.ingsw.client;

public enum ClientItemTileType {
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
    ClientItemTileType(String color) {
        this.color = color;
    }
}
