package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * This class represents the message that contains the board to be set.
 */
public class BoardSetMessage implements ServerMessage{

    /**
     * The message to be sent.
     */
    private final String message;
    /**
     * The board to be set.
     */
    private final ItemTileType[][] gridBoard;

    /**
     * The constructor of the class.
     *
     * @param message The message to be sent.
     * @param gridBoard The board to be set.
     */
    public BoardSetMessage(String message, ItemTileType[][] gridBoard) {
        this.message = message;
        this.gridBoard = gridBoard;
    }

    /**
     * Get the message to be sent.
     *
     * @return the message to be sent.
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Get the board to be set.
     *
     * @return the board to be set.
     */
    public ItemTileType[][] getGridBoard() {
        return gridBoard;
    }

    /**
     * This method is used to call the right method of the serverMessageHandler in order to handle this message.
     *
     * @param serverMessageHandler is the serverMessageHandler which has the right method to handle this message.
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}