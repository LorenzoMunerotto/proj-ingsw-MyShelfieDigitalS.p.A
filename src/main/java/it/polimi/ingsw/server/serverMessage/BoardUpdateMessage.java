package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.Coordinate;

import java.util.List;

/**
 * This class represents the message that contains the board to update.
 */
public class BoardUpdateMessage implements ServerMessage {

    private final String message;
    private final List<Coordinate> coordinates;
    private final long checksum;

    public BoardUpdateMessage(String message, List<Coordinate> coordinates, long checksum) {
        this.message = message;
        this.coordinates = coordinates;
        this.checksum = checksum;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public long getChecksum() {
        return checksum;
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
