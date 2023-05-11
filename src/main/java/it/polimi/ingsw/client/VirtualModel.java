package it.polimi.ingsw.client;

import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.server.serverMessage.ServerMessage;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.CRC32;

/**
 * This class represents the virtual model, for the proxy pattern.
 * It defines new data structures for the game objects, with the essential information for the view.
 */
public class VirtualModel {

    /**
     * A new data structure that represents the board.
     */
    private ItemTileType[][] board;
    /**
     * The common goal cards.
     */
    private List<Triplet<Integer, Integer, String>> commonGoalCards;
    /**
     * A new data structure that represents the personal goal card.
     */
    private ItemTileType[][] personalGoalCard;
    /**
     * The number of tiles in the bag.
     */
    private int numOfTilesInBag;

    /**
     * Map of the username and the library.
     */
    private final Map<String, ItemTileType[][]> clientUsernameLibrary = new HashMap<>();
    /**
     * Map of the username and the points.
     */
    private final Map<String, Integer> clientUsernamePoints = new HashMap<>();


    /**
     * The username of the client.
     */
    private String myUsername;
    /**
     * The current player username.
     */
    private String currentPlayerUsername;

    private int numberPersonalCard;


    public int getNumberPersonalCard() {
        return numberPersonalCard;
    }

    /**
     * The index of the first player.
     */
    private int firstPlayerIndex;
    /**
     * The username of the first player that has the full library.
     */
    private String firstFullLibraryUsername;
    /**
     * Is the last message received from the server.
     */
    private ServerMessage serverMessage;

    /**
     * Default constructor.
     * Maybe it should initialize at least the username and the list of players?
     */
    public VirtualModel() {
    }

    /**************************************************************************
     *                                    Board                               *
     **************************************************************************/

    /**
     * Get the board.
     *
     * @return the board.
     */
    public ItemTileType[][] getBoard() {
        return this.board;
    }

    /**
     * Set the board.
     *
     * @param gridBoard the board.
     */
    public void setBoard(ItemTileType[][] gridBoard) {
        this.board = gridBoard;
    }

    /**
     * Update the board when some tiles are removed.
     *
     * @param coordinates the coordinates of the new tiles.
     * @param checksum the checksum of the board.
     */
    public void updateBoard(List<Coordinate> coordinates, long checksum) {
        for(Coordinate coordinate : coordinates) {
            board[coordinate.getRow()][coordinate.getColumn()] = ItemTileType.EMPTY;
        }
        long newChecksum = calculateCRCBoard();
        if(newChecksum != checksum) {
            System.err.println("Error: Checksum mismatch. Board state might be inconsistent.");
        }
    }

    /**
     * Update the board after the refill.
     *
     * @param board the new board.
     * @param checksum the checksum of the board.
     */
    public void updateBoard(ItemTileType[][] board, long checksum) {
        this.board = board;
        long newChecksum = calculateCRCBoard();
        if(newChecksum != checksum) {
            System.err.println("Error: Checksum mismatch. Board state might be inconsistent.");
        }
    }

    /**
     * Calculate the checksum of the board.
     *
     * @return the checksum of the board.
     */
    public long calculateCRCBoard() {
        CRC32 crc = new CRC32();
        for (ItemTileType[] itemTileTypes : board) {
            for (int col = 0; col < board[0].length; col++) {
                crc.update(itemTileTypes[col].toString().getBytes());
            }
        }
        return crc.getValue();
    }

    /**************************************************************************
     *                                  Library                               *
     **************************************************************************/

    /**
     * Get the library.
     *
     * @return the library.
     */
    public ItemTileType[][] getLibrary() {
        return clientUsernameLibrary.get(myUsername);
    }

    /**
     * Set the library.
     *
     * @param username the username of the player.
     * @param library the library.
     */
    public void setLibrary(String username, ItemTileType[][] library) {
        clientUsernameLibrary.put(username, library);
    }

    /**
     * Update the library.
     *
     * @param username the username of the player.
     * @param itemTileTypeList the list of the tiles.
     * @param column the column of the library.
     * @param checksum the checksum of the library.
     */
    public void updateLibraryByUsername(String username, List<ItemTileType> itemTileTypeList, int column, long checksum) {
        ItemTileType[][] library = clientUsernameLibrary.get(username);
        for (ItemTileType itemTile : itemTileTypeList) {
            for (int row = library.length - 1; row >= 0; row--) {
                if (library[row][column] == ItemTileType.EMPTY) {
                    library[row][column] = itemTile;
                    break;
                }
            }
        }
        long newChecksum = calculateCRCLibrary(username);
        if(newChecksum != checksum) {
            System.err.println("Error: Checksum mismatch. Library state might be inconsistent.");
        }
    }

    /**
     * Calculate the checksum of the library.
     *
     * @param username the username of the player.
     * @return the checksum of the library.
     */
    public long calculateCRCLibrary(String username) {
        CRC32 crc = new CRC32();
        for (ItemTileType[] itemTileTypes : clientUsernameLibrary.get(username)) {
            for (int col = 0; col < clientUsernameLibrary.get(username)[0].length; col++) {
                crc.update(itemTileTypes[col].toString().getBytes());
            }
        }
        return crc.getValue();
    }

    /**************************************************************************
     *                               Personal Card                            *
     **************************************************************************/

    /**
     * Get the personal goal card.
     *
     * @return the personal goal card.
     */
    public ItemTileType[][] getPersonalGoalCard() {
        return this.personalGoalCard;
    }


    /**
     * Set the personal goal card as a new data structure.
     *
     * @param personalGoalCard the personal goal card.
     */
    public void setPersonalGoalCard(ItemTileType[][] personalGoalCard, int numberPersonalCard) {
        this.personalGoalCard = personalGoalCard;
        this.numberPersonalCard = numberPersonalCard;
    }

    /**************************************************************************
     *                                 Common Card                            *
     **************************************************************************/

    public Map<String, ItemTileType[][]> getClientUsernameLibrary() {
        return clientUsernameLibrary;
    }

    /**
     * Get the common goal cards.
     *
     * @return the common goal cards.
     */
    public List<Triplet<Integer, Integer, String>> getCommonGoalCards() {
        return this.commonGoalCards;
    }

    /**
     * Update the common goal cards.
     *
     * @param updatedCommonGoalCards the updated common goal cards.
     */
    public void setCommonGoalCards(List<Triplet<Integer, Integer, String>> updatedCommonGoalCards) {
        this.commonGoalCards = updatedCommonGoalCards;
    }

    public void updateCommonCardPoints(int index, int points) {
        for (int i = 0; i < commonGoalCards.size(); i++) {
            Triplet<Integer, Integer, String> card = commonGoalCards.get(i);
            if (card.getValue0().equals(index)) {
                commonGoalCards.set(i, new Triplet<>(index, points, card.getValue2()));
                break;
            }
        }
    }

    /**************************************************************************
     *                                    Bag                                 *
     **************************************************************************/

    /**
     * Get the bag.
     *
     * @return the bag.
     */
    public int getBag() {
        return this.numOfTilesInBag;
    }

    /**
     * Update the bag.
     *
     * @param numOfTilesInBag the number of tiles in the bag.
     */
    public void updateBag(int numOfTilesInBag) {
        this.numOfTilesInBag = numOfTilesInBag;
    }

    /**************************************************************************
     *                               Current Player                           *
     **************************************************************************/

    /**
     * Get the current player.
     *
     * @return the current player.
     */
    public String getCurrentPlayerUsername() {
        return this.currentPlayerUsername;
    }

    /**
     * Update the current player.
     *
     * @param currentPlayerUsername the current player username.
     */
    public void updateCurrentPlayerUsername(String currentPlayerUsername) {
        this.currentPlayerUsername = currentPlayerUsername;
    }

    /**************************************************************************
     *                               Client username                          *
     **************************************************************************/
    /**
     * Get the username of the client.
     *
     * @return the username of the client.
     */
    public String getMyUsername() {
        return myUsername;
    }

    /**
     * Set the username of the client.
     *
     * @param myUsername the username of the client.
     */
    public void setMyUsername(String myUsername) {
        this.myUsername = myUsername;
    }

    /**************************************************************************
     *                                   Others                               *
     **************************************************************************/

    /**
     * Updated the points in the ClientUsername-Points Map.
     *
     * @param username is the username of the player
     * @param points   are the points of the player
     */
    public void updatePointsByUsername(String username, Integer points) {

        if (clientUsernamePoints.containsKey(username)) {
            clientUsernamePoints.replace(username, points);
        } else {
            clientUsernamePoints.put(username, points);
        }
    }

    /**
     * Return the map of the username and the points
     */
    public Map<String, Integer> getClientUsernamePoints() {
        return clientUsernamePoints;
    }

    /**
     * Get the current leader board of the game.
     *
     * @return the current leader board of the game.
     */
    public List<Pair<String, Integer>> getLeaderBoard() {
        List<Pair<String, Integer>> leaderBoards = new ArrayList<>();
        clientUsernamePoints.forEach((username, points) -> leaderBoards.add(new Pair<>(username, points)));

        return leaderBoards.stream().sorted(Comparator.comparingInt(Pair<String, Integer>::getValue1).reversed()).collect(Collectors.toList());
    }

    public void setFirstFullLibraryUsername(String firstFullLibraryUsername) {
        this.firstFullLibraryUsername = firstFullLibraryUsername;
    }

    /**************************************************************************
     *                               Server Message                           *
     **************************************************************************/

    /**
     * Get the server message.
     *
     * @return the server message.
     */
    public ServerMessage getServerMessage() {
        return serverMessage;
    }

    /**
     * Set the server message.
     *
     * @param serverMessage the server message.
     */
    public void setServerMessage(ServerMessage serverMessage) {
        this.serverMessage = serverMessage;
    }

}
