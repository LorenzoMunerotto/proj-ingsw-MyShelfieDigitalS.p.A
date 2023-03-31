package it.polimi.ingsw.model.logic;

import it.polimi.ingsw.model.logic.personal_cards.PersonalGoalCard;

public class Player {

    private final String username;
    private boolean chair;
    private PersonalGoalCard personalGoalCard;

    public Player(String username) {
        this.username = username;
        this.chair = false;
        this.personalGoalCard = null;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean hasChair() {
        return this.chair;
    }

    public void setChair(boolean chair) {
        this.chair = chair;
    }

    public PersonalGoalCard getPersonalGoalCard() {
        return this.personalGoalCard;
    }

    public void setPersonalGoalCard(PersonalGoalCard personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
    }
}
