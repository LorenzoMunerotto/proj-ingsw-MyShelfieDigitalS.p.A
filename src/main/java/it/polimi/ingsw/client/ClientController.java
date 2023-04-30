package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.client.view.VirtualModel;

/**
 * This class represents the controller of the client.
 * Don't know if it is needed and how to implement it.
 */
public class ClientController {

    private VirtualModel virtualModel;
    private View view;

    public ClientController(VirtualModel virtualModel, View view) {
        this.virtualModel = virtualModel;
        this.view = view;
    }

    public void createGame(String username, int playersNumber) {

    }

    public void joinGame(String username) {

    }

    public void playTurn(String coordinates, int column) {

    }
}
