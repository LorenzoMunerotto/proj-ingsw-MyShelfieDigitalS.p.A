package it.polimi.ingsw.client;

import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;
import java.util.zip.CRC32;

/**
 * This class represents the virtual model, for the proxy pattern.
 * It defines new data structures for the game objects, with the essential information for the view.
 */
public class VirtualModel {

    /**
     * Map of the username and the library.
     */
    private final Map<String, ItemTileType[][]> clientUsernameLibrary = new HashMap<>();
    /**
     * Map of the username and the points.
     */
    private final Map<String, Integer> clientUsernamePoints = new HashMap<>();
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
     * It is index of the personal goal card.
     * Used for the GUI, to know which personal goal card to show.
     */
    private int indexPersonalGoalCard;
    /**
     * The username of the client.
     */
    private String myUsername;
    private int currentPlayerIndex;
    private List<String> orderedListOfPlayers;
    /**
     * Is the last message received from the server.
     */
    private String serverMessage;

    /**
     * Default constructor.
     */
    public VirtualModel() {

    }

    public int getNumberPersonalCard() {
        return this.indexPersonalGoalCard;
    }

    /**************************************************************************
     *                                    Board                               *
     **************************************************************************/

    /**
     * Get the board.
     *
     * @return the board
     */
    public ItemTileType[][] getBoard() {
        return this.board;
    }

    /**
     * Set the board.
     *
     * @param gridBoard the board
     */
    public void setBoard(ItemTileType[][] gridBoard) {
        this.board = gridBoard;
    }

    /**
     * Update the board when some tiles are removed.
     *
     * @param coordinates the coordinates of the new tiles
     * @param checksum    the checksum of the board
     */
    public void updateBoard(List<Coordinate> coordinates, long checksum) {
        for (Coordinate coordinate : coordinates) {
            board[coordinate.getRow()][coordinate.getColumn()] = ItemTileType.EMPTY;
        }
        long newChecksum = calculateCRCBoard();
        if (newChecksum != checksum) {
            System.err.println("Error: Checksum mismatch. Board state might be inconsistent.");
        }
    }

    /**
     * Update the board after the refill.
     *
     * @param board    the new board
     * @param checksum the checksum of the board
     */
    public void updateBoard(ItemTileType[][] board, long checksum) {
        this.board = board;
        long newChecksum = calculateCRCBoard();
        if (newChecksum != checksum) {
            System.err.println("Error: Checksum mismatch. Board state might be inconsistent.");
        }
    }

    /**
     * Calculate the checksum of the board.
     *
     * @return the checksum of the board
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
     * @return the library
     */
    public ItemTileType[][] getLibrary() {
        return clientUsernameLibrary.get(myUsername);
    }

    /**
     * Set the library.
     *
     * @param username the username of the player
     * @param library  the library
     */
    public void setLibrary(String username, ItemTileType[][] library) {
        clientUsernameLibrary.put(username, library);
        clientUsernamePoints.put(username, 0);
    }

    /**
     * Update the library.
     *
     * @param username         the username of the player
     * @param itemTileTypeList the list of the tiles
     * @param column           the column of the library
     * @param checksum         the checksum of the library
     */
    public void updateLibraryByUsername(String username, List<ItemTileType> itemTileTypeList, int column,
                                        long checksum) {
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
        if (newChecksum != checksum) {
            System.err.println("Error: Checksum mismatch. Library state might be inconsistent.");
        }
    }

    /**
     * Calculate the checksum of the library.
     *
     * @param username the username of the player
     * @return the checksum of the library
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
     * @return the personal goal card
     */
    public ItemTileType[][] getPersonalGoalCard() {
        return this.personalGoalCard;
    }

    /**
     * Set the personal goal card as a new data structure.
     *
     * @param personalGoalCard the personal goal card
     */
    public void setPersonalGoalCard(ItemTileType[][] personalGoalCard, int numberPersonalCard) {
        this.personalGoalCard = personalGoalCard;
        this.indexPersonalGoalCard = numberPersonalCard;
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
     * @return the common goal cards
     */
    public List<Triplet<Integer, Integer, String>> getCommonGoalCards() {
        return this.commonGoalCards;
    }

    /**
     * Update the common goal cards.
     *
     * @param updatedCommonGoalCards the updated common goal cards
     */
    public void setCommonGoalCards(List<Triplet<Integer, Integer, String>> updatedCommonGoalCards) {
        this.commonGoalCards = updatedCommonGoalCards;
    }

    /**
     * Update the points of the common goal card.
     *
     * @param index  is the index of the card
     * @param points is the new points of the card
     */
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
     *                               Current Player                           *
     **************************************************************************/


    public void setPlayerIndex(List<String> usernames) {
        this.orderedListOfPlayers = usernames;
    }

    public String getPlayerUsername(Integer index) {
        if (index >= 0 && index < orderedListOfPlayers.size()) {
            return this.orderedListOfPlayers.get(index);
        } else {
            throw new IllegalArgumentException("Invalid index");
        }
    }
    /**
     * Get the current player username.
     *
     * @return the current player username
     */
    public Integer getPlayerIndex(String username) {
        return this.orderedListOfPlayers.indexOf(username);
    }

    public String getCurrentPlayerUsername() {
        if (this.currentPlayerIndex >= 0 && this.currentPlayerIndex < orderedListOfPlayers.size()) {
            return this.orderedListOfPlayers.get(this.currentPlayerIndex);
        } else {
            throw new IllegalStateException("Invalid current player index");
        }
    }

    /**
     * Get the current player index.
     *
     * @return the current player index
     */
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    /**
     * Update the current player.
     *
     * @param currentPlayerUsernameIndex the current player username and index
     */
    public void updateCurrentPlayerUsernameIndex(Pair<String, Integer> currentPlayerUsernameIndex) {
        this.currentPlayerIndex = currentPlayerUsernameIndex.getValue1();
    }

    /**************************************************************************
     *                               Client username                          *
     **************************************************************************/

    /**
     * Get the username of the client.
     *
     * @return the username of the client
     */
    public String getMyUsername() {
        return myUsername;
    }

    /**
     * Set the username of the client.
     *
     * @param myUsername the username of the client
     */
    public void setMyUsername(String myUsername) {
        this.myUsername = myUsername;
    }


    /**************************************************************************
     *                                   Points                               *
     **************************************************************************/

    /**
     * Get the current leader board of the game.
     *
     * @param username is the username of the player
     * @return the points of the player
     */
    public int getPointsByUsername(String username) {
        Integer points = this.clientUsernamePoints.get(username);
        if (points == null) {
            throw new IllegalArgumentException("Username not found");
        } else {
            return points;
        }
    }

    /**
     * Updated the points in the ClientUsername-Points Map.
     *
     * @param username is the username of the player
     * @param points   are the points of the player
     */
    public void updatePointsByUsername(String username, Integer points) {
        if (this.clientUsernamePoints.containsKey(username)) {
            this.clientUsernamePoints.replace(username, points);
        } else {
            throw new IllegalArgumentException("Username not found");
        }
    }

    /**
     * Get the current leader board of the game.
     *
     * @return the current leader board of the game.
     */
    public List<Pair<String, Integer>> getLeaderBoard() {
        List<Pair<String, Integer>> leaderBoard = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : clientUsernamePoints.entrySet()) {
            leaderBoard.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        leaderBoard.sort((o1, o2) -> {
            int scoreComparison = o2.getValue1().compareTo(o1.getValue1());
            if (scoreComparison != 0) {
                return scoreComparison;
            } else {
                Integer index1 = getPlayerIndex(o1.getValue0());
                Integer index2 = getPlayerIndex(o2.getValue0());
                if (index1 == -1 || index2 == -1) {
                    throw new IllegalStateException("Usernames in the leaderboard not found in the list of players");
                } else {
                    return index1.compareTo(index2);
                }
            }
        });
        return leaderBoard;
    }

    /**************************************************************************
     *                               Server Message                           *
     **************************************************************************/

    /**
     * Get the server message.
     *
     * @return the server message
     */
    public String getServerMessage() {
        return serverMessage;
    }

    /**
     * Set the server message.
     *
     * @param serverMessage the server message
     */
    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}