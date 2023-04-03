package it.polimi.ingsw.model.gameMechanics;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.model.gameMechanics.personal_cards.Goal;
import it.polimi.ingsw.model.gameMechanics.personal_cards.PersonalGoalCard;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    void getPersonalGoalCard() throws IOException {
        /*
        String pathFile = "configPersonalCard" + String.valueOf(1) + ".json";
        File nomeFileJson =new File(pathFile);
        ObjectMapper om = new ObjectMapper();

        ArrayList<Goal> pGC2 = om.readValue(nomeFileJson, new TypeReference<ArrayList<Goal>>() {});
        Goal goal = pGC2.get(0);
        int k= pGC2.get(0).getRow();
        int j= pGC2.get(1).getColumn();
        int z=k;
        PersonalGoalCard pGC1 =new PersonalGoalCard(1);
        assertEquals(pGC2,pGC1);
         */

    }

    @Test
    void setPersonalGoalCard() {
    }
}