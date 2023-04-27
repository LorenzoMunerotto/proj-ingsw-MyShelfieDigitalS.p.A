package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class BoardUpdateMessage implements ServerMessage {

    private final String message;
    private final ItemTileType[][] gridBoard;

    public BoardUpdateMessage(String message, ItemTileType[][] gridBoard) {
        this.message = message;
        this.gridBoard = gridBoard;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ItemTileType[][] getGridBoard() {
        return gridBoard;
    }
}
