package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameMechanics.personal_cards.PersonalGoalCard;

import java.io.IOException;

public class Player {
    //int numberPersonalCard;
    private final String username;
    private boolean chair;
    private PersonalGoalCard personalGoalCard;

    private int totPoints;

    /**
     * create a Player object
     * @param username is Player name
     * @param numberPersonalCard is a random number for extraction of the Personal Card
     */
    public Player(String username) throws IOException {
    //public Player(String username, int numberPersonalCard) throws IOException {
        this.username = username;
        this.chair = false;
        this.totPoints =totPoints;
        //this.numberPersonalCard = numberPersonalCard;
        //this.personalGoalCard = new PersonalGoalCard(numberPersonalCard);
    }

    public int getTotPoints() {
        return totPoints;
    }

    public void setTotPoints(int totPoints) {
        this.totPoints = totPoints;
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
