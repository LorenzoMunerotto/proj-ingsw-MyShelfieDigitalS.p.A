package it.polimi.ingsw.model.logic.personal_cards;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonalGoalCard {
    private int numberCard;
    private File fileJson;

    public ArrayList<Goal> goals;
    public ArrayList<Goal> getGoals() {
        return goals;
    }

    /**
     * create the File, JSON file to Java object
     * @param randomNumber number between 1 and 12 to choose the PersonalCards
     *  @return file
     */
    public File createFile(int randomNumber){
        String pathFile = "configPersonalCard" + String.valueOf(randomNumber) + ".json";
        File nomeFileJson =new File(pathFile);
        return nomeFileJson;
    }

    public PersonalGoalCard(int numberCard) throws IOException {
        this.numberCard = numberCard;
        this.fileJson = createFile(numberCard);
        this.goals = createGoals(fileJson);
    }

    /**
     * ObjectMapper instantiation, eserialization into the `Goal` class
     * Creating Object of ObjectMapper define in Jackson API
     * @param fileJson
     * @return
     * @throws IOException
     */
    public ArrayList<Goal> createGoals(File fileJson) throws IOException {
        ObjectMapper om = new ObjectMapper();
        ArrayList<Goal> personalGoalCard = om.readValue(fileJson, new TypeReference<ArrayList<Goal>>() {});
        return personalGoalCard;
    };


}
