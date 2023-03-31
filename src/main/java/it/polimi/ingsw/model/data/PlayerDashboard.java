package it.polimi.ingsw.model.data;

import it.polimi.ingsw.model.logic.personal_cards.PersonalGoalCard;

/**
 * This class represents the player's dashboard.
 */
public class PlayerDashboard {
    /**
     * This attribute represents the player's library.
     */
    private final Library library;
    /**
     * This attribute represents the player's points.
     */
    private final int points;
    /**
     * This attribute represents the player's personal goal card.
     */
    private PersonalGoalCard personalGoalCard;

    /**
     * PlayerDashboard constructor, initializes the player's library and points.
     */
    public PlayerDashboard(){
        this.points = 0;
        this.library = new Library();
    }

    /**
     * Get the player's points.
     *
     * @return the player's points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Get the player's library.
     *
     * @return the player's library
     */
    public Library getLibrary() {
        return this.library;
    }

    /**
     * Get the player's personal goal card.
     *
     * @return the player's personal goal card
     */
    public PersonalGoalCard getPersonalGoalCard() {
        return this.personalGoalCard;
    }

    /**
     * Set the player's personal goal card.
     *
     * @param personalGoalCard the player's personal goal card
     */
    public void setPersonalGoalCard(PersonalGoalCard personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
    }
}
