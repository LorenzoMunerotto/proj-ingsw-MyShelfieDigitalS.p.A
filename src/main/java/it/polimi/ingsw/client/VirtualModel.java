package it.polimi.ingsw.client;

import it.polimi.ingsw.client.clientEntity.ClientBoardCell;

import it.polimi.ingsw.client.clientEntity.ClientCommonCard;
import it.polimi.ingsw.client.clientEntity.ClientLibrary;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.server.serverMessage.ServerMessage;
import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents the virtual model, for the proxy pattern.
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
     * The index of the first player.
     */
    private int firstPlayerIndex;
    /**
     * A new data structure that represents the board.
     */
    private ClientBoardCell[][] board;
    /**
     * Map of the username and the library.
     */
    private final Map<String, ClientLibrary> clientUsernameLibrary = new HashMap<>();
    /**
     * Map of the username and the points.
     */
    private final Map<String, Integer> clientUsernamePoints = new HashMap<>();
    /**
     * The common goal cards.
     */
    private List<ClientCommonCard> commonGoalCards;
    /**
     * A new data structure that represents the personal goal card.
     */
    private ClientLibrary personalGoalCard;
    /**
     * The number of tiles in the bag.
     */
    private int numOfTilesInBag;
    /**
     * The username of the first player that has the full library.
     */
    private String firstFullLibraryUsername;

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
    public List<ClientCommonCard> getCommonGoalCards() {
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
    public String getCurrentPlayerUsername() {
        return this.currentPlayerUsername;
    }

    /**
     * Update the board.
     *
     * @param gridBoard is the updated board.
     * @param playableGrid is the updated playable grid.
     */
    public void updateBoard(ItemTileType[][] gridBoard, boolean[][] playableGrid) {
        ClientBoardCell[][] board = new ClientBoardCell[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                board[row][col] = new ClientBoardCell(gridBoard[row][col], playableGrid[row][col]);
            }
        }
        this.board = board;
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
     * Updated the points in the ClientUsername-Points Map.
     *
     * @param username is the username of the player
     * @param points are the points of the player
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
    public void setCommonGoalCards (List<ClientCommonCard> updatedCommonGoalCards){
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

    public String getMyUsername() {
        return myUsername;
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

    public String getFirstFullLibraryUsername() {
        return firstFullLibraryUsername;
    }

    public void setFirstFullLibraryUsername(String firstFullLibraryUsername) {
        this.firstFullLibraryUsername = firstFullLibraryUsername;
    }

    public void updateCommonCardByIndex(Integer index, Integer pointsAvailable){
        for (ClientCommonCard clientCommonCard: commonGoalCards){
            if (clientCommonCard.getIndex().equals(index)){
                clientCommonCard.setCurrentPoints(pointsAvailable);
            }
        }
    }
}
