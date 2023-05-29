package it.polimi.ingsw.model.gameState.events;

/**
 * Class defining the event of the first full library.
 */
public class FirstFullLibraryEvent implements ModelEvent {

    /**
     * The username of the player who has the first full library.
     */
    private final String username;

    /**
     * Default constructor.
     *
     * @param username is the username of the player who has the first full library
     */
    public FirstFullLibraryEvent(String username) {
        this.username = username;
    }

    /**
     * Get the username of the player who has the first full library.
     *
     * @return the username of the player who has the first full library
     */
    public String getUsername() {
        return username;
    }

    /**
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}