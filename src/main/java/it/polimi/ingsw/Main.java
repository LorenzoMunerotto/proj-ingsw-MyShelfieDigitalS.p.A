package it.polimi.ingsw;

import it.polimi.ingsw.model.gameState.GameData;
import it.polimi.ingsw.model.gameState.events.VirtualGameData;
import it.polimi.ingsw.terminal.Controller;
import it.polimi.ingsw.terminal.Drawer;

/**
 * java says hello
 */
public class Main {
    public static void main(String[] args) {


        System.out.println("My Shelfie!");


        GameData gameData = new GameData();
        Drawer drawer = new Drawer(gameData);
        Controller controller = new Controller(gameData, drawer);
        controller.addListener(drawer);

        gameData.notifyAllListeners(new VirtualGameData(gameData));




    }

}


