package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.personal_cards.CardsContainer;
import it.polimi.ingsw.model.gameState.Exceptions.IllegalUsernameException;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() throws IllegalUsernameException {
        player = new Player("Pippo");
    }

    @Test
    @DisplayName("Test constructor with invalid username")
    void testConstructorInvalidUsername() {
        assertThrows(IllegalUsernameException.class, () -> new Player(null));
        assertThrows(IllegalUsernameException.class, () -> new Player(""));
        assertThrows(IllegalUsernameException.class, () -> new Player(" "));
    }

    @Test
    @DisplayName("Test set points")
    void testSetTotPoints() {
        player.setTotPoints(5);
        assertEquals(5, player.getTotPoints());
    }

    @Test
    @DisplayName("Test set chair")
    void testSetChair() {
        player.setChair(true);
        assertTrue(player.hasChair());
    }

    @Test
    @DisplayName("Test set personal goal card")
    void testSetPersonalGoalCard() {
        CardsContainer cardsContainer = new CardsContainer();
        PersonalGoalCard personalGoalCard = cardsContainer.getPersonalGoalCards(1).get(0);

        player.setPersonalGoalCard(personalGoalCard);
        assertEquals(personalGoalCard, player.getPersonalGoalCard());
    }

    @Test
    @DisplayName("Test set library")
    void testSetLibrary() {
        Library library = new Library();
        player.setLibrary(library);
        assertEquals(library, player.getLibrary());
    }
}