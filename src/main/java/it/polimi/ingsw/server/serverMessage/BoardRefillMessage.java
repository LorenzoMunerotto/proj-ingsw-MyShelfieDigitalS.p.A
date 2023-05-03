package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class BoardRefillMessage implements ServerMessage {

    private final String message;
    private final ItemTileType[][] gridBoard;
    private final boolean[][] playableGrid;

    public BoardRefillMessage(ItemTileType[][] gridBoard, boolean[][] playableGrid) {
        this.message = "Board refilled";
        this.gridBoard = gridBoard;
        this.playableGrid=playableGrid;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ItemTileType[][] getGridBoard() {
        return gridBoard;
    }

    public boolean[][] getPlayableGrid() {
        return playableGrid;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
