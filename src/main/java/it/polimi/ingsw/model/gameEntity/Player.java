package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.AbstractListenable;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;
import it.polimi.ingsw.model.gameState.events.PersonalCardSetEvent;

/**
 * Class representing a player.
 */
public class Player extends AbstractListenable {

    /**
     * Client ID of the player.
     */
    private final int clientID;
    /**
     * Player username.
     */
    private final String username;
    /**
     * True if the player has the chair.
     */
    private boolean chair;
    /**
     * Personal goal card of the player.
     */
    private PersonalGoalCard personalGoalCard;
    /**
     * Library of the player.
     */
    private Library library;
    /**
     * Total points of the player.
     */
    private int totPoints;

    /**
     * Creates a Player object.
     * ClientId soon
     *
     * @param username is the Player's name
     */
    public Player(String username, Integer clintID) {
        this.username = username;
        this.chair = false;
        this.totPoints =0;
        this.clientID=clintID;
        this.library = new Library();
    }

    /**
     * Get the total points of the player.
     *
     * @return the total points of the player
     */
    public int getTotPoints() {
        return totPoints;
    }

    /**
     * Set the total points of the player.
     *
     * @param totPoints the new total points of the player
     */
    public void setTotPoints(int totPoints) {
        this.totPoints = totPoints;
    }

    /**
     * Get the username of the player.
     *
     * @return the username of the player
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Get if the player has the chair.
     *
     * @return true if the player has the chair, false otherwise
     */
    public boolean hasChair() {
        return this.chair;
    }

    /**
     * Set if the player has the chair.
     *
     * @param chair the new chair value
     */
    public void setChair(boolean chair) {
        this.chair = chair;
    }

    /**
     * Get the personal goal card of the player.
     *
     * @return the personal goal card of the player
     */
    public PersonalGoalCard getPersonalGoalCard() {
        return this.personalGoalCard;
    }

    /**
     * Set the personal goal card of the player.
     *
     * @param personalGoalCard the new personal goal card of the player
     */
    public void setPersonalGoalCard(PersonalGoalCard personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
        notifyAllListeners(new PersonalCardSetEvent(personalGoalCard));
    }

    /**
     * Get the library of the player.
     *
     * @return the library of the player
     */
    public Library getLibrary() {
        return this.library;
    }

    /**
     * Set the library of the player.
     *
     * @param library the new library of the player
     */
    public void setLibrary(Library library) {
        this.library = library;
    }

    /**
     * Get the clientID of the player.
     *
     * @return the clientID of the player
     */
    public int getClintID() {
        return this.clientID;
    }
}
