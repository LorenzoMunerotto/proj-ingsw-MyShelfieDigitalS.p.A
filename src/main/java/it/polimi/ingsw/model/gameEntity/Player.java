package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameState.Exceptions.IllegalUsernameException;
import it.polimi.ingsw.model.gameMechanics.personal_cards.PersonalGoalCard;

public class Player {
    //int numberPersonalCard;
    private final String username;
    private boolean chair;
    private PersonalGoalCard personalGoalCard;




    private Library library;

    private int totPoints;

    /**
     * create a Player object, ClientId soon
     * @param username is Player name

     */
    public Player(String username) throws IllegalUsernameException {

        if(username == null || username.trim().isEmpty()){
            throw new IllegalUsernameException();
        }

        //public Player(String username, int numberPersonalCard) throws IOException {
        this.username = username;
        this.chair = false;
        this.totPoints =0;
        this.library = new Library();
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

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }



}
