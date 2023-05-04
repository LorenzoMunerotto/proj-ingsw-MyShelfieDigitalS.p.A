package it.polimi.ingsw.model.gameMechanics;

public enum BreakRules {
    TILES_NOT_ALIGNED("The selected tiles are not aligned"),
    ALONE_TILE("One of the selected tiles has no adjacent tile"),
    SURROUNDED_TILE("One of the selected tiles does not have a free side"),
    COLUMN_OUT_OF_SPACE("The selected column does not have enough space to contain all of the selected tiles"),
    GENERAL_ILLEGAL_MOVE(" "),
    EMPTY_CELL("One of the selected cells is empty"),
    // client side
    TOO_MUCH_TILES_SELECTED ("More than 3 tiles have been selected"),
    DUPLICATE_TILES_SELECTED("A cell can be selected only once"),
    NOT_PLAYABLE_TILE("One of the selected cells does not exist");

    private final String description;

    BreakRules(String description){
        this.description=description;
    }

    public String getDescription() {
        return description;
    }
}


