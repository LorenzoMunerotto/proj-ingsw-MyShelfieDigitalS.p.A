package it.polimi.ingsw.model.logic;

import it.polimi.ingsw.model.logic.personal_cards.PersonalGoalCard;

import java.io.IOException;

public class Player {
    int numberPersonalCard;
    private final String username;
    private boolean chair;
    private PersonalGoalCard personalGoalCard;

    /**
     * create a Player object
     * @param username is Player name
     * @param numberPersonalCard is a random number for extraction of the Personal Card
     */
    public Player(String username, int numberPersonalCard) throws IOException {
        this.username = username;
        this.chair = false;
        this.numberPersonalCard = numberPersonalCard;
        this.personalGoalCard = new PersonalGoalCard(numberPersonalCard);
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
