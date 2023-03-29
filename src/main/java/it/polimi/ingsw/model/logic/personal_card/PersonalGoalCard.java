package it.polimi.ingsw.model.logic.personal_card;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.model.logic.personal_card.PersonalConstraint;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class PersonalGoalCard {

    private String name;
    private List<PersonalConstraint> constraints;
    public PersonalGoalCard(){
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            constraints = Collections.singletonList(mapper.readValue(new File("personal_goal_card.json"), PersonalConstraint.class));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
