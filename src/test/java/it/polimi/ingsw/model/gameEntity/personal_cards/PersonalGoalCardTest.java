package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonalGoalCardTest {

    private CardsContainer cardsContainer;

    @BeforeEach
    public void setUp() {
        cardsContainer = new CardsContainer();
    }

    @Test
    @DisplayName("Test cards creation for 2, 3, and 4 players")
    public void testCardsCreationForMultiplePlayers() {
        testCardsForNumberOfPlayers(2);
        testCardsForNumberOfPlayers(3);
        testCardsForNumberOfPlayers(4);
    }

    /**
     * Helper method to test cards creation for a specific number of players.
     *
     * @param numOfPlayers the number of players
     */
    private void testCardsForNumberOfPlayers(int numOfPlayers) {
        List<PersonalGoalCard> personalGoalCards = cardsContainer.getPersonalGoalCards(numOfPlayers);
        ObjectMapper objectMapper = new ObjectMapper();
        List<PersonalGoalCard> originalCards;

        try {
            originalCards = objectMapper.readValue(new File("src/main/resources/configPersonalGoalsCards.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, PersonalGoalCard.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON file: " + e.getMessage());
        }

        for (PersonalGoalCard generatedCard : personalGoalCards) {
            boolean foundMatch = false;

            for (PersonalGoalCard originalCard : originalCards) {
                if (generatedCard.getNumber() == originalCard.getNumber()) {
                    foundMatch = true;
                    assertEquals(originalCard.getGoals().size(), generatedCard.getGoals().size(), "The number of goals do not match");

                    for (int i = 0; i < originalCard.getGoals().size(); i++) {
                        Goal originalGoal = originalCard.getGoals().get(i);
                        Goal generatedGoal = generatedCard.getGoals().get(i);

                        assertEquals(originalGoal.getRow(), generatedGoal.getRow(), "The row doesn't match");
                        assertEquals(originalGoal.getColumn(), generatedGoal.getColumn(), "The column doesn't match");
                        assertEquals(originalGoal.getItemTileType(), generatedGoal.getItemTileType(), "The item tile type doesn't match");
                    }
                    break;
                }
            }
            assertTrue(foundMatch, "The generated card does not match any of the original cards");
        }
    }
}