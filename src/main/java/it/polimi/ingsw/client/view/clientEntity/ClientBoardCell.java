package it.polimi.ingsw.client.view.clientEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class ClientBoardCell {

    private ItemTileType type;
    private boolean playable;


    public ClientBoardCell(ItemTileType type, boolean playable) {
        this.type = type;
        this.playable = playable;
    }

    public ItemTileType getType() {
        return type;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setType(ItemTileType type) {
        this.type = type;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }
}
