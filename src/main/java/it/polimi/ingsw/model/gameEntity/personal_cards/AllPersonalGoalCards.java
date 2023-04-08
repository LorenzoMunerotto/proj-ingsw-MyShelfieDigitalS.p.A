package it.polimi.ingsw.model.gameEntity.personal_cards;

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

    public void setCards(List<PersonalGoalCard> cards) {
        this.cards = cards;
    }
}
