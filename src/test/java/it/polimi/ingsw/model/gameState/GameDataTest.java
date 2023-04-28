package it.polimi.ingsw.model.gameState;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameState.exceptions.IllegalNumOfPlayersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameDataTest {
    private GameData gameData;

    @BeforeEach
    void setUp() {
        gameData = new GameData();
    }

    @Test
    @DisplayName("Test constructor")
    void testConstructor() {
        assertNotNull(gameData.getBag());
        assertNull(gameData.getBoard());
        assertEquals(0, gameData.getNumOfPlayers());
        assertEquals(0, gameData.getCurrentNumOfPlayers());
        assertTrue(gameData.getPlayers().isEmpty());
        assertFalse(gameData.getFirstFullLibraryUsername().isPresent());
    }

    @Test
    @DisplayName("Test setNumOfPlayers")
    void testSetNumOfPlayers() {
        assertDoesNotThrow(() -> gameData.setNumOfPlayers(2));
        assertDoesNotThrow(() -> gameData.setNumOfPlayers(4));
        assertThrows(IllegalNumOfPlayersException.class, () -> gameData.setNumOfPlayers(1));
        assertThrows(IllegalNumOfPlayersException.class, () -> gameData.setNumOfPlayers(5));
    }

    @Test
    @DisplayName("Test addPlayer")
    void testAddPlayer() {
        int initialNumOfPlayers = gameData.getCurrentNumOfPlayers();
        Player player = new Player("TestPlayer", 0);
        gameData.addPlayer(player);
        assertEquals(initialNumOfPlayers + 1, gameData.getCurrentNumOfPlayers());
    }

    @Test
    @DisplayName("Test setBoard based on the number of players")
    void testSetBoard() {
        Board board = new Board(2);
        gameData.setBoard(board);
        assertNotNull(gameData.getBoard());
    }

    @Test
    @DisplayName("Test setCommonGoalCardsList")
    void testSetCommonGoalCardsList() {
        List<CommonGoalCard> commonGoalCardsList = CommonCardFactory.createCards();
        gameData.setCommonGoalCardsList(commonGoalCardsList);
        assertEquals(commonGoalCardsList, gameData.getCommonGoalCardsList());
    }

    @Test
    @DisplayName("Test setFirstFullLibraryUsername")
    void testSetFirstFullLibraryUsername() {
        String username = "TestPlayer";
        gameData.setFirstFullLibraryUsername(username);
        assertTrue(gameData.getFirstFullLibraryUsername().isPresent());
        assertEquals(username, gameData.getFirstFullLibraryUsername().get());
    }
}
