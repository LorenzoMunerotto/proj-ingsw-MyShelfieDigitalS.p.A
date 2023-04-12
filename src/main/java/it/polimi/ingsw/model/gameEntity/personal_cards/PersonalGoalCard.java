package it.polimi.ingsw.model.gameEntity.personal_cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a personal goal card.
 */
public class PersonalGoalCard {
    /**
     * Number of the card.
     */
    private int number;
    /**
     * Goals of the card.
     */
    public List<Goal> goals;

    /**
     * Constructor of the class.
     */
    public PersonalGoalCard() {
    }

    /**
     * Constructor of the class.
     *
     * @param number Number of the card.
     * @param goals Goals of the card.
     */
    public PersonalGoalCard(int number, ArrayList<Goal> goals) {
        this.number = number;
        this.goals = goals;
    }

    /**
     * Get the number of the card.
     *
     * @return Number of the card.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set the number of the card.
     *
     * @param number Number of the card.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get the goals of the card.
     *
     * @return Goals of the card.
     */
    public List<Goal> getGoals() {
        return goals;
    }

    /**
     * Set the goals of the card.
     *
     * @param goals Goals of the card.
     */
    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Personal Card " + number + " ");
            for (Goal goal: goals){
               str.append("[").append(goal.getRow()).append(",").append(goal.getColumn()).append("]").append(":").append(goal.getItemTileType()).append("  ");
            }
        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalGoalCard that = (PersonalGoalCard) o;
        return number == that.number && goals.equals(that.goals);
    }


}
