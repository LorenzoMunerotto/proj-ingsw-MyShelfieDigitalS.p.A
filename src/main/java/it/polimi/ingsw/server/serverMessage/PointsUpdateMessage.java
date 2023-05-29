package it.polimi.ingsw.server.serverMessage;

public class PointsUpdateMessage implements ServerMessage {

    /**
     * The message
     */
    private final String message;
    /**
     * The player's username
     */
    private final String username;
    /**
     * The player's points
     */
    private final int points;

    /**
     * Default constructor.
     * @param message the message
     * @param username the player's username
     * @param points the point's username
     */
    public PointsUpdateMessage(String message, String username, int points) {
        this.message = message;
        this.username = username;
        this.points = points;
    }

    /**
     * Get the player's username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the player's point updated
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
