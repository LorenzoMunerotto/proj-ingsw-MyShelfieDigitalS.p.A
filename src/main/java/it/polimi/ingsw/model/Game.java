package it.polimi.ingsw.model;

import it.polimi.ingsw.model.gameEntity.ItemTile;

import it.polimi.ingsw.model.gameEntity.personal_cards.AllPersonalGoalCards;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;
import it.polimi.ingsw.model.gameMechanics.BoardManager;
import it.polimi.ingsw.model.gameMechanics.LibraryManager;
import it.polimi.ingsw.model.gameMechanics.PointsManager;

import it.polimi.ingsw.model.gameState.GameData;
import org.javatuples.Pair;

import java.util.*;

/**
 * This class represents the game.
 */
public class Game {
    /**
     * This attribute represents the game data.
     */
    private final GameData gameData;

    /**
     * This constructor creates a new game.
     */
    public Game(){
        this.gameData=new GameData();
    }

    /**
     * This method starts the game.
     *
     * @param coordinates the coordinates of the item tiles to be grabbed
     * @param column the column where the item tiles will be placed
     */
    public void play(List<Pair<Integer, Integer>> coordinates, int column){

        BoardManager boardManager = new BoardManager(gameData.getBoard(), gameData.getBag());
        LibraryManager libraryManager = new LibraryManager(gameData.getCurrentPlayer().getLibrary());
        PointsManager pointsManager = new PointsManager(gameData.getCurrentPlayer(), gameData.getNumOfPlayers(),gameData.getCommonGoalCardsList(),gameData.getFirstFullLibraryUsername());

        int numberOfTiles = coordinates.size();
        //sequenza critica
        libraryManager.hasEnoughSpace(column,numberOfTiles);
        List<ItemTile> itemTileList = boardManager.grabItemTiles(coordinates);
        libraryManager.insertItemTiles(column, itemTileList);
        //

        if (libraryManager.isFull()){
            gameData.setFirstFullLibraryUsername(gameData.getCurrentPlayer().getUsername());
        }

        pointsManager.updateTotalPoints();
        gameData.nextPlayer();

        if( boardManager.isRefillTime()){
            boardManager.refillBoard();
        }
    }

    /**
     * This method returns the game data.
     *
     * @return the game data
     */
    public GameData getGameData() {

        return gameData;
    }

    /**
     * This method initializes the board.
     */
    public void boardInitialization(){
        BoardManager boardManager = new BoardManager(gameData.getBoard(),gameData.getBag());
        if( boardManager.isRefillTime()){
            boardManager.refillBoard();
        }
    }

    /**
     * This method assigns all the personal cards.
     */
    public void assignAllPersonalCard(){

        Set<Integer> numberOfPersonalCards = new HashSet<>();
        Random random = new Random();
        AllPersonalGoalCards allPersonalGoalCards = AllPersonalGoalCards.makeAllPersonalGoalCards();
        for(int i=0; i<gameData.getNumOfPlayers();i++){
            while (true){
                int randomNumber= random.nextInt(12);
                if(!numberOfPersonalCards.contains(randomNumber)){
                    PersonalGoalCard randomPersonalGoalCard = allPersonalGoalCards.getCards().get(randomNumber);

                    gameData.getPlayer(i).setPersonalGoalCard(randomPersonalGoalCard);
                    numberOfPersonalCards.add(randomNumber);
                    break;
                }
            }
        }
    }
}
