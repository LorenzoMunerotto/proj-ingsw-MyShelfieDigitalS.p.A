package it.polimi.ingsw.model.personal_cards;
import java.util.ArrayList;

public class PersonalGoalCard {

    private ArrayList<SingleGoal> goals;


    public PersonalGoalCard(ArrayList<SingleGoal> goals) {
        this.goals = goals;
    }

    public ArrayList<SingleGoal> getGoals() {
        return goals;
    }

    public void setGoals(ArrayList<SingleGoal> goals) {
        this.goals = goals;
    }

}