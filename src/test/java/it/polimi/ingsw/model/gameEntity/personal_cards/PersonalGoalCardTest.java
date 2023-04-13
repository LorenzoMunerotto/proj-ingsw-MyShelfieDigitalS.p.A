package it.polimi.ingsw.model.gameEntity.personal_cards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonalGoalCardTest {

  @Test
  public void testCreationCards(){
    CardsContainer cardsContainer = new CardsContainer();
    List<PersonalGoalCard> cards = cardsContainer.getPersonalGoalCards(2);
    assertEquals(2, cards.size());
  }

  @ParameterizedTest(name = "verify - {index}")
  @CsvFileSource(resources = "/personalCard.csv")
  void personalCard(Integer index, String personalCardAsString){

    //assertEquals(CardsContainer.makeAllPersonalGoalCards().getPersonalGoalCard(index),PersonalGoalCardHelper.newPersonalGoalCardFromString(personalCardAsString));

  }
}