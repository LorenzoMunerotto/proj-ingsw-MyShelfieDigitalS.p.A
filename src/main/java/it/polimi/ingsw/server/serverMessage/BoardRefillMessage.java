package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class BoardRefillMessage implements ServerMessage {

    private final String message;
    private final ItemTileType[][] gridBoard;
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
