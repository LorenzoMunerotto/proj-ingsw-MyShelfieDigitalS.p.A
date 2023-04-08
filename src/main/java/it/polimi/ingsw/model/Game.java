package it.polimi.ingsw.model;

import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;
import it.polimi.ingsw.model.gameMechanics.BoardManager;
import it.polimi.ingsw.model.gameMechanics.LibraryManager;
import it.polimi.ingsw.model.gameMechanics.PointsManager;
import it.polimi.ingsw.model.gameState.Exceptions.GameStartedException;
import it.polimi.ingsw.model.gameState.Exceptions.IllegalUsernameException;
import it.polimi.ingsw.model.gameState.Exceptions.UsernameAlreadyExistsException;
import it.polimi.ingsw.model.gameState.GameData;
import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {

    private GameData gameData;


    public Game(){
        this.gameData=new GameData();
    }

    /*
    questo metodo si occupa di gestire la stringa che l'utente immette come nickname a prescindere che sia
    il primo giocatore collegato o pure uno dei successivi
    l'username scelto dall'utente può sollevare due tipi di eccezioni diverse, ma in ogni caso all'utente
    verrà fatto riscegliere l'username spiegando perchè quello precedente non era ammissibile
    se il metodo termina senza sollevare eccezioni il risultatato è aver creato un Player
    con un username corretto e averlo aggiunto alla lista dei player in gameData
    dopo aver aggiunto il player alla lista di player si controlla se si è arrivati al numOfPlayerstabilito
    e in tal caso si setta started in gameState a true


    public void manageNewUser(String username) {

        Player newPlayer = null;
        try {
            newPlayer = new Player(username);
        } catch (IllegalUsernameException e) {
            System.out.println(e.getMessage());
        }
        //a questo punto se è il primo giocatore dovrebbe essere chiesto con quanti giocatori vuole giocare
        if (gameData.getNumOfPlayers()==0){

            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader tastiera = new BufferedReader(input);
            Integer numOfPlayer;

            while(gameData.getNumOfPlayers()==0) {
                try {
                    System.out.print("1st player, select numberOfPlayer: ");
                    numOfPlayer = Integer.valueOf(tastiera.readLine());
                    gameData.setNumOfPlayers(numOfPlayer);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

        }
        try {
            gameData.addPlayer(newPlayer);
        } catch (UsernameAlreadyExistsException e) {
            System.out.println(e.getMessage());

        } catch (GameStartedException e) {

            System.out.println(e.getMessage());
        }
    }


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

        if (gameData.getCurrentPlayer().getLibrary().isFull()){
            gameData.setFirstFullLibraryUsername(gameData.getCurrentPlayer().getUsername());
        }

        pointsManager.updateTotalPoints();
        gameData.nextPlayer();

        if( boardManager.isRefillTime()){
            boardManager.refillBoard();
        }

    }
    public GameData getGameData() {

        return gameData;
    }

    public void boardInitialization(){
        BoardManager boardManager = new BoardManager(gameData.getBoard(),gameData.getBag());
        if( boardManager.isRefillTime()){
            boardManager.refillBoard();
        }
    }


    public void assignAllPersonalCard(){

        Set<Integer> numberOfPersonalCards = new HashSet<>();
        Random random = new Random();
        for(int i=0; i<gameData.getNumOfPlayers();i++){
            while (true){
                int randomNumber= random.nextInt(12);
                if(!numberOfPersonalCards.contains(randomNumber)){
                    PersonalGoalCard randomPersonalGoalCard = null;

                    randomPersonalGoalCard = PersonalGoalCard.makePersonalGoalCard(randomNumber);

                    gameData.getPlayer(i).setPersonalGoalCard(randomPersonalGoalCard);
                    numberOfPersonalCards.add(randomNumber);
                    break;
                }
            }
        }
    }
}
