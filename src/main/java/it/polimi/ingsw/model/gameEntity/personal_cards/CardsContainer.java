package it.polimi.ingsw.model.gameEntity.personal_cards;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardsContainer {

    private List<PersonalGoalCard> personalGoalCards;

    public CardsContainer(){
        initializeDeck();
    }

    public void initializeDeck(){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            personalGoalCards = objectMapper.readValue(new File("src/main/resources/configPersonalGoalsCards.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, PersonalGoalCard.class));
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON file: " + e.getMessage());
        }
    }

    public List<PersonalGoalCard> getPersonalGoalCards(int numOfPlayers) {
        List<PersonalGoalCard> cardsCopy = new ArrayList<>(personalGoalCards);
        Collections.shuffle(cardsCopy);
        /*for(int i = 0; i < numOfPlayers; i++){
            System.out.println("Card number: " + cardsCopy.get(i).getNumber());
            for(Goal goal: cardsCopy.get(i).getGoals())
                System.out.println("Goal:\n Row: " + goal.getRow() + ", Column: " + goal.getColumn() + ", Type: " + goal.getItemTileType());
        }*/
        return cardsCopy.stream().limit(numOfPlayers).collect(Collectors.toList());
    }
}
