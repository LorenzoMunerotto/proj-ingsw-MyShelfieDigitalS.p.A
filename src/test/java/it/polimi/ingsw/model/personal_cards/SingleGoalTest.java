package it.polimi.ingsw.model.personal_cards;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class SingleGoalTest {

    SingleGoal singolGoal;

    @BeforeEach
    void setUp() {
        try {
            singolGoal = new SingleGoal()  ;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    void testSingolGoalCostractur(){
        assertEquals(0, singolGoal.getRow());
    }
}