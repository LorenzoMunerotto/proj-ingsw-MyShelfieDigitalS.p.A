package it.polimi.ingsw.client;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.HashMap;

public class ModelView {
    private ClientItemTileType[][] clientBoard;
    private ClientLibrary[] libraries;
    private String playerName;
    HashMap<String, ClientLibrary> clientNameLibrabry = new HashMap<String, ClientLibrary>();
    private int playerIndex;
    private CommonCardClient[] commonCardClients;
    private ClientLibrary personalCard;
    private int[] totPoints;




}
