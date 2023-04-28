package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;

public class PersonalCardEvent extends ModelEvent {

    private final PersonalGoalCard personalGoalCard;

    public PersonalCardEvent(PersonalGoalCard personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
    }

    public PersonalGoalCard getPersonalGoalCard() {
        return personalGoalCard;
    }
}
