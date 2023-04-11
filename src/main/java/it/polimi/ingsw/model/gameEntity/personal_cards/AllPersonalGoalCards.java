package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AllPersonalGoalCards {

    List<PersonalGoalCard> cards;


    public AllPersonalGoalCards(List<PersonalGoalCard> cards) {
        this.cards = cards;
    }

    public AllPersonalGoalCards() {

    }

    public List<PersonalGoalCard> getCards() {
        return cards;
    }


    public static AllPersonalGoalCards makeAllPersonalGoalCards()  {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = PersonalGoalCard.class.getResourceAsStream("/configPersonalGoalsCards.json");


        AllPersonalGoalCards allPersonalGoalCards = null;
        try {
            allPersonalGoalCards = mapper.readValue(is, AllPersonalGoalCards.class);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return allPersonalGoalCards;
    }

    public void setCards(List<PersonalGoalCard> cards) {
        this.cards = cards;
    }


    public PersonalGoalCard getPersonalGoalCard(int index){
        return cards.get(index);
    }


}
