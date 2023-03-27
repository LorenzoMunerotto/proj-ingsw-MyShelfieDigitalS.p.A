package it.polimi.ingsw.model;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.logic.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameDataTest {

    GameData gameData;
    List<String> players;
    List<CommonGoalCard> commonGoalCards;
    @Test
    public void testConstructor(){
        players = new ArrayList<>();
        players.add("Pluto");
        players.add("Pippo");
        players.add("Paperino");
        commonGoalCards = CommonCardFactory.createCards(3);
        gameData = new GameData(players, 3, commonGoalCards);
        assertNotNull(gameData);
    }

}