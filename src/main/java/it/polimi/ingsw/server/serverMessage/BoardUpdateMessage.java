package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class BoardUpdateMessage implements ServerMessage {

    private final String message;
    private final ItemTileType[][] gridBoard;

    private final boolean[][] playableGrid;

    public BoardUpdateMessage(String message, ItemTileType[][] gridBoard, boolean[][] playableGrid) {
        this.message = message;
        this.gridBoard = gridBoard;
        this.playableGrid = playableGrid;
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
