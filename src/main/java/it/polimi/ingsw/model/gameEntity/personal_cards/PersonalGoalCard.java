package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PersonalGoalCard {
    private int number;


    public ArrayList<Goal> goals;


    public PersonalGoalCard() {
    }

    public PersonalGoalCard(int number, ArrayList<Goal> goals) {
        this.number = number;
        this.goals = goals;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }

    public static PersonalGoalCard makePersonalGoalCard(int number)  {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = PersonalGoalCard.class.getResourceAsStream("/configPersonalGoalsCards.json");



        AllPersonalGoalCards allPersonalGoalCards = null;
        try {
            allPersonalGoalCards = mapper.readValue(is, AllPersonalGoalCards.class);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return allPersonalGoalCards.getCards().get(number);
    }

    @Override

    public String toString() {
        String str ="Personal Card "+number+" ";
            for (Goal goal: goals){
               str+= "["+goal.getRow()+","+goal.getColumn()+"]"+":"+goal.getItemTileType()+"  ";
            }
        return str;
    }


}
