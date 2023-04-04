package it.polimi.ingsw.model.gameEntity.personal_cards;

import java.util.ArrayList;

public class AllPersonalGoalCard {

    public ArrayList<PersonalGoalCard> personalGoalCard;

    public AllPersonalGoalCard(ArrayList<PersonalGoalCard> personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
    }
    //private final static String pathFile = "jsonList.json";
    //File nomeFile = new File("jsonList.json");

    /*
    public File getNomeFile() {
        return nomeFile;
    } */

    public ArrayList<PersonalGoalCard> getPersonalGoalCard() {
        return personalGoalCard;
    }
}
