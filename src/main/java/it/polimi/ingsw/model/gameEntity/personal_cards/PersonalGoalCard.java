package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class representing a personal goal card.
 */
public class PersonalGoalCard {
    /**
     * The number of the personal goal card.
     */
    private final int number;
    /**
     * The list of the goals of the personal goal card.
     */
    private final List<Goal> goals;

    /**
     * Constructor of the class.
     *
     * @param number the number of the personal goal card
     * @param goals  the list of the goals of the personal goal card
     */
    public PersonalGoalCard(@JsonProperty("number") int number, @JsonProperty("goals") List<Goal> goals) {
        this.number = number;
        this.goals = goals;
    }

    /**
     * Get the number of the personal goal card.
     *
     * @return the number of the personal goal card
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get the list of the goals of the personal goal card.
     *
     * @return the list of the goals of the personal goal card
     */
    public List<Goal> getGoals() {
        return goals;
    }

    /**
     * Get the string representation of the personal goal card.
     *
     * @return the string representation of the personal goal card
     */
    @Override
    public String toString() {
        return "PersonalGoalCard{" +
                "number=" + number +
                ", goals=" + goals +
                '}';
    }
}