package it.polimi.ingsw.model.personal_cards;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllPersonalCardsTest {

    AllPersonalCards allPersonalCards;
    @BeforeEach
    void sutUp(){
        try {
            allPersonalCards = new AllPersonalCards()  ;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void AllPersonalCardsContructor(){
        assertEquals(0, allPersonalCards.getAllpersonalCards().get(0).getGoals().get(0).getRow());
    }

}