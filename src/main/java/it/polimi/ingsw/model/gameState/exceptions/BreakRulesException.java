package it.polimi.ingsw.model.gameState.exceptions;

/**
 * This class represents the exception thrown when a player tries to perform an action that breaks the rules of the game.
 */
public class BreakRulesException extends Exception {

    /**
     * The type of exception.
     */
    private final BreakRules type;

    /**
     * Default constructor, initializes the exception type.
     *
     * @param type the type of exception
     */
    public BreakRulesException(BreakRules type) {
        this.type = type;
    }

    /**
     * Get the type of exception.
     *
     * @return the type of exception
     */
    public BreakRules getType() {
        return type;
    }

    /**
     * Get the message of the exception.
     *
     * @return the message of the exception
     */
    @Override
    public String getMessage() {
        return type.getDescription();
    }
}