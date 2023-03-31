package it.polimi.ingsw.model.logic.personal_cards;

import java.io.File;
import java.util.ArrayList;

public class PersonalGoalCard {
    private int numberCard;
    private String pathFile ="configPersonalCard";
    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public void createPath(int randomNumber){
        pathFile = pathFile + String.valueOf(randomNumber) + ".json";
        File nomeFileJson =new File(pathFile);
    }



    public ArrayList<Goal> goals;
}
