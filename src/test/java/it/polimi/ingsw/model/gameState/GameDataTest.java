package it.polimi.ingsw.model.gameState;

import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameState.Exceptions.GameStartedException;
import it.polimi.ingsw.model.gameState.Exceptions.IllegalUsernameException;
import it.polimi.ingsw.model.gameState.Exceptions.InvalidNumOfPlayers;
import it.polimi.ingsw.model.gameState.Exceptions.UsernameAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameDataTest {

    private GameData gameData;

    @BeforeEach
    public void setUp() {
        gameData = new GameData();
    }

    @Test
    @DisplayName("Test constructor")
    public void testConstructor() {
        assertEquals(0, gameData.getNumOfPlayers());
        assertEquals(0, gameData.getCurrentNumOfPlayers());
        assertFalse(gameData.isStarted());
        assertFalse(gameData.getFirstFullLibraryUsername().isPresent());
        assertEquals(2, gameData.getCommonGoalCardsList().size());
        assertNotEquals(gameData.getCommonGoalCardsList().get(0), gameData.getCommonGoalCardsList().get(1));
    }

    @Test
    @DisplayName("Test set number of players")
    public void testSetNumOfPlayers() throws InvalidNumOfPlayers {
        gameData.setNumOfPlayers(2);
        assertEquals(2, gameData.getNumOfPlayers());
        assertDoesNotThrow(() -> gameData.setNumOfPlayers(2));
        assertThrows(InvalidNumOfPlayers.class, () -> gameData.setNumOfPlayers(5));
        assertThrows(InvalidNumOfPlayers.class, () -> gameData.setNumOfPlayers(1));
    }

    @Test
    @DisplayName("Test add player")
    public void testAddPlayer() throws InvalidNumOfPlayers, IllegalUsernameException {
        gameData.setNumOfPlayers(3);

        Player player1 = new Player("Pippo");
        Player player2 = new Player("Paperino");
        Player player3 = new Player("Pluto");
        Player player4 = new Player("Topolino");

        assertDoesNotThrow(() -> gameData.addPlayer(player1));
        assertThrows(UsernameAlreadyExistsException.class, () -> gameData.addPlayer(new Player("Pippo")));

        assertDoesNotThrow(() -> gameData.addPlayer(player2));
        assertDoesNotThrow(() -> gameData.addPlayer(player3));

        assertTrue(gameData.getPlayers().contains(player1));
        assertTrue(gameData.getPlayers().contains(player2));
        assertTrue(gameData.getPlayers().contains(player3));

        assertEquals(3, gameData.getCurrentNumOfPlayers());
        assertThrows(GameStartedException.class, () -> gameData.addPlayer(player4));
    }

    @Test
    @DisplayName("Test change the current player")
    public void testNextPlayer() throws InvalidNumOfPlayers, UsernameAlreadyExistsException, GameStartedException, IllegalUsernameException {
        gameData.setNumOfPlayers(3);

        Player player1 = new Player("Pippo");
        Player player2 = new Player("Paperino");
        Player player3 = new Player("Pluto");

        gameData.addPlayer(player1);
        gameData.addPlayer(player2);
        gameData.addPlayer(player3);

        Integer initialPlayerIndex = gameData.getCurrentPlayerIndex();

        gameData.nextPlayer();
        assertNotEquals(initialPlayerIndex, gameData.getCurrentPlayerIndex());

        gameData.nextPlayer();
        assertNotEquals(initialPlayerIndex, gameData.getCurrentPlayerIndex());

        gameData.nextPlayer();
        assertEquals(initialPlayerIndex, gameData.getCurrentPlayerIndex());
    }

    @Test
    @DisplayName("Test set first full library username")
    public void testSetFirstFullLibraryUsername() {
        assertFalse(gameData.getFirstFullLibraryUsername().isPresent());
        gameData.setFirstFullLibraryUsername("Pippo");
        assertTrue(gameData.getFirstFullLibraryUsername().isPresent());
        assertEquals("Pippo", gameData.getFirstFullLibraryUsername().get());
    }
}
