package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class BoardRefillMessage implements ServerMessage {

    private final String message;
    private final ItemTileType[][] gridBoard;

    public BoardRefillMessage(ItemTileType[][] gridBoard) {
        this.message = "Board refilled";
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
