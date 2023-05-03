package it.polimi.ingsw.client.clientEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class ClientLibrary {

        private ItemTileType[][] itemTileTypesGrid;

    public ClientLibrary(ItemTileType[][] itemTileTypesGrid) {
        this.itemTileTypesGrid = itemTileTypesGrid;
    }

    public ItemTileType[][] getItemTileTypesGrid() {
        return itemTileTypesGrid;
    }
}
