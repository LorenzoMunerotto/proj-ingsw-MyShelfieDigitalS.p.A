package it.polimi.ingsw.model.gameMechanics;

/**
 * Enum that contains all the possible break rules.
 */
public enum BreakRules {
    TILES_NOT_ALIGNED("The selected tiles are not aligned"),
    ALONE_TILE("One of the selected tiles has no adjacent tile"),
    SURROUNDED_TILE("One of the selected tiles doesn't have a free side"),
    EMPTY_CELL("One of the selected cells is empty"),
    TOO_MUCH_TILES_SELECTED ("More than 3 tiles have been selected"),
    TOO_FEW_TILES_SELECTED("Less than 1 tile has been selected"),
    DUPLICATE_TILES_SELECTED("A cell can be selected only once"),
    NOT_PLAYABLE_TILE("One of the selected cells does not exist"),
    COLUMN_OUT_OF_BOUNDS("The selected column does not exist"),
    COLUMN_OUT_OF_SPACE("The selected column does not have enough space to contain all of the selected tiles"),
    GENERAL_ILLEGAL_MOVE(" ");

    /**
     * The description of the break rule.
     */
    private final String description;

    /**
     * Constructor of the enum.
     *
     * @param description the description of the break rule
     */
    BreakRules(String description){
        this.description=description;
    }

    /**
     * Get the description of the break rule.
     *
     * @return the description of the break rule
     */
    public String getDescription() {
        return description;
    }
}