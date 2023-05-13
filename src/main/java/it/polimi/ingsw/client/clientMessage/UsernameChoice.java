package it.polimi.ingsw.client.clientMessage;

/**
 * This class represents the message that the client sends when he chooses his username.
 */
public class UsernameChoice implements ClientMessage {

    /**
     * The username chosen by the client.
     */
    private final String username;

    /**
     * The constructor of the class.
     *
     * @param username the username chosen by the client
     */
    public UsernameChoice(String username) {
        this.username = username;
    }

    /**
     * Get the username chosen by the client.
     *
     * @return the username chosen by the client
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the message.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return null;
    }
}