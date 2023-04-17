package it.polimi.ingsw.terminal;

import it.polimi.ingsw.*;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameState.events.Move;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameMechanics.BoardManager;
import it.polimi.ingsw.model.gameMechanics.LibraryManager;
import it.polimi.ingsw.model.gameMechanics.PointsManager;
import it.polimi.ingsw.model.gameState.GameData;
import it.polimi.ingsw.model.gameState.events.NumOfPlayerChoice;
import it.polimi.ingsw.model.gameState.events.UsernameChoice;
import it.polimi.ingsw.model.gameState.events.VirtualGameData;

import java.util.*;

public class Controller extends AbstractListenable implements Listener, EventHandler {

    GameData gameData;

    public Controller(GameData gameData, Drawer drawer) {
        super();
        drawer.addListener(this);
        this.gameData = gameData;
    }


    /**
     * handle the numOfPlayerChoice event
     * tries to modify the gameData, if an exception is thrown notifies the event to the view
     * @param numOfPlayerChoice
     */
    public void handle(NumOfPlayerChoice numOfPlayerChoice) {
        try {
            gameData.setNumOfPlayers(numOfPlayerChoice.getNumOfPlayer());
        } catch (Exception e) {
            notifyAllListeners(numOfPlayerChoice);
        }
    }

    @Override
    public void handle(VirtualGameData virtualGameData) {
        //deve rimanere così per ora
    }

    /**
     * handle the move event
     * tries to modify the gameData, if an exception is thrown notifies the event to the view
     * @param move
     */
    public void handle(Move move) {

        BoardManager boardManager = new BoardManager(gameData.getBoard(), gameData.getBag());
        LibraryManager libraryManager = new LibraryManager(gameData.getCurrentPlayer().getLibrary());
        PointsManager pointsManager = new PointsManager(gameData.getCurrentPlayer(), gameData.getNumOfPlayers(),gameData.getCommonGoalCardsList(),gameData.getFirstFullLibraryUsername());

        int numberOfTiles = move.getCoordinateList().size();

        try {
            libraryManager.hasEnoughSpace(move.getColumn(), numberOfTiles);
            List<ItemTile> itemTileList = boardManager.grabItemTiles(move.getCoordinateList());
            libraryManager.insertItemTiles(move.getColumn(), itemTileList);
        }catch (Exception e){
            notifyAllListeners(move); //se la mossa infrange qualche regola si notifica la view
        }

        if (libraryManager.isFull()){
            gameData.setFirstFullLibraryUsername(gameData.getCurrentPlayer().getUsername());
        }

        pointsManager.updateTotalPoints();


        if( boardManager.isRefillTime()){
            boardManager.refillBoard();
        }

        gameData.nextPlayer();
    }


    /**
     * handle the usernameChoice event
     * tries to modify the gameData, if an exception is thrown notifies the event to the view
     * @param usernameChoice
     */
    public void handle(UsernameChoice usernameChoice) {

        Player newPlayer = null;
        try {
            newPlayer = new Player(usernameChoice.getUsername());
            gameData.addPlayer(newPlayer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            notifyAllListeners(usernameChoice);
        }


    }

    /**
     * This is part of Observable-Observer pattern
     * @param event
     */
    @Override
    public void update(Event event) {
        event.accept(this);
    }


}
