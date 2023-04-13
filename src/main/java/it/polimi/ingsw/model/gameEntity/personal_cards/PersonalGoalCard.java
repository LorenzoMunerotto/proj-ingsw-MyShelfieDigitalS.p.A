package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class representing a personal goal card.
 */
public class PersonalGoalCard {
    private final int number;
    private final List<Goal> goals;

    public PersonalGoalCard(@JsonProperty("number") int number, @JsonProperty("goals") List<Goal> goals) {
        this.number = number;
        this.goals = goals;
    }

    public int getNumber() {
        return number;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    @Override
    public String toString() {
        return "PersonalGoalCard{" +
                "number=" + number +
                ", goals=" + goals +
                '}';
    }
}
