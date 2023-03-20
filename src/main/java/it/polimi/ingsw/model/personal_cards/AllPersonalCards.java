package it.polimi.ingsw.model.personal_cards;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class AllPersonalCards {
    public void setAllpersonalCards(List<PersonalGoalCard> allpersonalCards) {
        this.allpersonalCards = allpersonalCards;
    }
    public List<PersonalGoalCard> getAllpersonalCards() {
        return allpersonalCards;
    }

    public AllPersonalCards(List<PersonalGoalCard> allpersonalCards) {
        this.allpersonalCards = allpersonalCards;
    }

    private List<PersonalGoalCard> allpersonalCards= new ArrayList<PersonalGoalCard>();
    private final static String pathFile = "configPersonalGoalsCards.json";

    public AllPersonalCards() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        allpersonalCards = List.of(objectMapper.readValue(pathFile, PersonalGoalCard[].class));
    }



}
