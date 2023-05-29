package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that contains all the personal goal cards.
 */
public class CardsContainer {
    /**
     * The list of all the personal goal cards.
     */
    private List<PersonalGoalCard> personalGoalCards;

    /**
     * Constructor of the class.
     * It creates the deck of personal goal cards.
     */
    public CardsContainer() {
        createDeck();
    }

    /**
     * Method that creates the deck of personal goal cards.
     */
    public void createDeck() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream is = CardsContainer.class.getResourceAsStream("/configPersonalGoalsCards.json");
            personalGoalCards = objectMapper.readValue(is, objectMapper.getTypeFactory().constructCollectionType(List.class, PersonalGoalCard.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON file: " + e.getMessage());
        }
    }

    /**
     * Get a list of personal goal cards, based on the number of players.
     *
     * @param numOfPlayers the number of players
     * @return the list of personal goal cards
     */
    public List<PersonalGoalCard> getPersonalGoalCards(int numOfPlayers) {
        List<PersonalGoalCard> cardsCopy = new ArrayList<>(personalGoalCards);
        Collections.shuffle(cardsCopy);
        return cardsCopy.stream().limit(numOfPlayers).collect(Collectors.toList());
    }

    /**
     * Get the list of all the personal goal cards.
     *
     * @return the list of all the personal goal cards
     */
    public List<PersonalGoalCard> getAllPersonalGoalCards() {
        return this.personalGoalCards;
    }
}