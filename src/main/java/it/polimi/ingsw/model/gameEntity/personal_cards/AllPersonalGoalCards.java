package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Class is used to get the object AllPersonalGoalCards from JSON file
 */
public class AllPersonalGoalCards {

    /**
     * list of all personal goal cards
     */
    List<PersonalGoalCard> cards;

    /**
     * Constructor of the class, initialize the list of all personal goal cards.
     *
     * @param cards list of all personal goal cards
     */
    public AllPersonalGoalCards(List<PersonalGoalCard> cards) {
        this.cards = cards;
    }

    /**
     * Get the list of all personal goal cards.
     *
     * @return list of all personal goal cards
     */
    public List<PersonalGoalCard> getCards() {
        return this.cards;
    }

    /**
     * This method get the object AllPersonalGoalCards from JSON file.
     *
     * @return allPersonalGoalCards object
     */
    public static AllPersonalGoalCards makeAllPersonalGoalCards()  {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = PersonalGoalCard.class.getResourceAsStream("/configPersonalGoalsCards.json");

        AllPersonalGoalCards allPersonalGoalCards;
        try {
            allPersonalGoalCards = mapper.readValue(is, AllPersonalGoalCards.class);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return allPersonalGoalCards;
    }

    /**
     * Set the list of all personal goal cards.
     *
     * @param cards list of all personal goal cards
     */
    public void setCards(List<PersonalGoalCard> cards) {
        this.cards = cards;
    }

    /**
     * Get the personal goal card at the specified index.
     *
     * @param index index of the personal goal card
     * @return personal goal card at the specified index
     */
    public PersonalGoalCard getPersonalGoalCard(int index){
        return cards.get(index);
    }
}
