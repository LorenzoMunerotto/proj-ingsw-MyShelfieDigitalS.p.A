package it.polimi.ingsw.model;

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
    //private PersonalGoalCard personalGoalCard;

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
}
