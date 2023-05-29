package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * This class represent the message that contains the board when it's refilled
 */
public class BoardRefillMessage implements ServerMessage {

    /**
     * The message to be sent.
     */
    private final String message;
    /**
     * The board refilled
     */
    private final ItemTileType[][] gridBoard;
    /**
     * The checksum
     */
    private final long checksum;

    public BoardRefillMessage(String message, ItemTileType[][] gridBoard, long checksum) {
        this.message = message;
        this.gridBoard = gridBoard;
        this.checksum = checksum;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ItemTileType[][] getGridBoard() {
        return gridBoard;
    }


    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }

    public long getChecksum() {
        return checksum;
    }
}
