package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonalGoalCard {
    private int number;


    public List<Goal> goals;


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

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }


    @Override

    public String toString() {
        String str ="Personal Card "+number+" ";
            for (Goal goal: goals){
               str+= "["+goal.getRow()+","+goal.getColumn()+"]"+":"+goal.getItemTileType()+"  ";
            }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalGoalCard that = (PersonalGoalCard) o;
        return number == that.number && goals.equals(that.goals);
    }


}
