package it.polimi.ingsw.model.gameEntity.personal_cards;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;



import static org.junit.jupiter.api.Assertions.assertEquals;




class PersonalGoalCardTest {


  @ParameterizedTest(name = "verify - {index}")
  @CsvFileSource(resources = "/personalCard.csv")
  void personalCard(Integer index, String personalCardAsString){

    assertEquals(AllPersonalGoalCards.makeAllPersonalGoalCards().getPersonalGoalCard(index),PersonalGoalCardHelper.newPersonalGoalCardFromString(personalCardAsString));

  }
}