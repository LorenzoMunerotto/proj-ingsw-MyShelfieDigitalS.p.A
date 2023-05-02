package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.view.clientEntity.ClientBoardCell;

import it.polimi.ingsw.client.view.clientEntity.ClientLibrary;
import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.server.serverMessage.ServerMessage;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the virtual model, for the proxy pattern.
 * Don't know how to update it.
 * A new model every time? Or with the update method below?
 * It defines new data structures for the game objects, with the essential information for the view.
 */
public class VirtualModel {

    private ServerMessage serverMessage;

    private String myUsername;
    /**
     * The current player username.
     */
    private String currentPlayerUsername;
    /**
     * A new data structure that represents the board.
     */
    private ClientBoardCell[][] board;

    private HashMap<String, ClientLibrary> clientUsernameLibrary = new HashMap<>();
    private HashMap<String, Integer> clientUsernamePoints = new HashMap<>();

    /**
     * The common goal cards.
     * Maybe a triplet that contains the current point, the index and the description.
     */
    private List<CommonGoalCard> commonGoalCards;
    /**
     * A new data structure that represents the personal goal card.
     */
    private ClientLibrary personalGoalCard;

    /**
     * The number of tiles in the bag.
     */
    private int numOfTilesInBag;



    /**
     * Default constructor.
     * Maybe it should initialize at least the username and the list of players?
     */
    public VirtualModel() {
    }

    /**
     * Get the board.
     *
     * @return the board.
     */
    public ClientBoardCell[][] getBoard() {
        return this.board;
    }

    /**
     * Get the library.
     *
     * @return the library.
     */
    public ClientLibrary getMyLibrary() {

        return clientUsernameLibrary.get(myUsername);
    }

    public ClientLibrary getLibraryByUsername(String username){
        return clientUsernameLibrary.get(username);
    }

    public int getPointsByUsername(String username){
        return clientUsernamePoints.get(username);
    }


    /**
     * Get the personal goal card.
     *
     * @return the personal goal card.
     */
    public ClientLibrary getPersonalGoalCard() {
        return this.personalGoalCard;
    }

    /**
     * Get the common goal cards.
     *
     * @return the common goal cards.
     */

    public List<CommonGoalCard> getCommonGoalCards() {
        return this.commonGoalCards;
    }



    /**
     * Get the bag.
     *
     * @return the bag.
     */

    public int getBag() {
        return this.numOfTilesInBag;
    }




    /**
     * Get the current player.
     *
     * @return the current player.
     */

    public String getCurrentPlayer() {
        return this.currentPlayerUsername;
    }

    /**
     * Update the board.
     *
     * @param updatedBoard the updated board.
     */
    public void updateBoard (ClientBoardCell[][] updatedBoard){
       this.board = updatedBoard;
    }

    /**
     * Update the library in the ClientUsername-Library Map.
     *
     * @param username the updated library owner.
     * @param library yhe updated library.
     */
    public void updateLibraryByUsername (String username, ClientLibrary library){

        if (clientUsernameLibrary.containsKey(username)){
            clientUsernameLibrary.replace(username,library);
        }
        else{
            //THIS BRANCH IS EXEC ONLY AT THE START OF THE GAME
            clientUsernamePoints.put(username, 0);
            clientUsernameLibrary.put(username,library);
        }
    }

    /**
     * Updated the Points in ClientUsername-Points Map
     * @param username
     * @param points
     */
    public void updatePointsByUsername (String username, Integer points){

        if (clientUsernamePoints.containsKey(username)){
            clientUsernamePoints.replace(username, points);
        }
        else{
            clientUsernamePoints.put(username, points);
        }
    }

        /**
     * Set the personal goal card.
     * It doesn't really need to be updated, it should be only set at the beginning of the game.
     *
     * @param clientLibrary the updated personal goal card.
     */
    public void setPersonalGoalCard (ClientLibrary clientLibrary){
        this.personalGoalCard = clientLibrary;
    }

    /**
     * Update the common goal cards.
     *
     * @param updatedCommonGoalCards the updated common goal cards.
     */
    public void updateCommonGoalCards (List<CommonGoalCard> updatedCommonGoalCards){
        this.commonGoalCards = updatedCommonGoalCards;
    }



    /**
     * Update the current player.
     *
     * @param currentPlayerUsername the current player username.
     */
    public void updateCurrentPlayerUsername(String currentPlayerUsername){
        this.currentPlayerUsername = currentPlayerUsername;
    }

    /**
     * Update the bag.
     *
     * @param numOfTilesInBag the bag.
     */
    public void updateBag(int numOfTilesInBag){
        this.numOfTilesInBag = numOfTilesInBag;
    }

    public void setMyUsername(String myUsername) {
        this.myUsername = myUsername;
    }

    public void setServerMessage(ServerMessage serverMessage) {
        this.serverMessage = serverMessage;
    }

    public ServerMessage getServerMessage() {
        return serverMessage;
    }

    public List<Pair<String, Integer>>  getLeaderBoard(){

        List<Pair<String, Integer>> leaderBoards = new ArrayList<>();
        clientUsernamePoints.forEach((username, points)-> leaderBoards.add(new Pair<>(username, points)));

        return leaderBoards.stream().sorted(Comparator.comparingInt(Pair<String,Integer>::getValue1).reversed()).collect(Collectors.toList());
    }
}
