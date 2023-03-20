package it.polimi.ingsw.model.personal_cards;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    /*
    private void toJavaObject() throws JsonProcessingException {


    }
    */
    /*
    final ObjectMapper objectMapper = new ObjectMapper();
    Language[] langs = objectMapper.readValue(json, Language[].class);*/

}