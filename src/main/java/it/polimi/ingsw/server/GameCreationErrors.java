package it.polimi.ingsw.server;

/**
 * This enum is used to send error messages to the client.
 */
public enum GameCreationErrors {

    DUPLICATE_USERNAME("This username is already taken, choose another one"),
    ILLEGAL_USERNAME("This username is not valid, choose another one"),
    ILLEGAL_NUM_OF_PLAYER("This number of players is not valid, choose another one");

    /**
     * The message of the error.
     */
    private final String description;

    /**
     * This constructor initializes the error message.
     *
     * @param description is the message of the error.
     */
    GameCreationErrors(String description) {
        this.description = description;
    }

    /**
     * Get the description of the error.
     *
     * @return the description of the error.
     */
    public String getDescription() {
        return this.description;
    }
}