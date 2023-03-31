package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.logic.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCardFactoryTest {

    @Test
    @DisplayName("Test creations of the common cards for 2 players")
    public void testCreateCardsFor2Players() {
        int numOfPlayers = 2;
        List<CommonGoalCard> commonGoalCardList = CommonCardFactory.createCards(numOfPlayers);
        assertEquals(2, commonGoalCardList.size());
        // il numero delle carte comuni è sempre 2 a prescindere dal numero di giocatori
        CommonGoalCard card1 = commonGoalCardList.get(0);
        CommonGoalCard card2 = commonGoalCardList.get(1);
        assertNotEquals(card1, card2);

        int indexCard1 = card1.getIndex();
        int indexCard2 = card2.getIndex();
        assertNotEquals(indexCard1, indexCard2);

        int points1 = card1.getPoints().get(0);
        assertEquals(8, points1);

        int points2 = card1.getPoints().get(1);
        assertEquals(4, points2);

        int tmp = card1.getHighestPoint();
        assertEquals(8, tmp);
        assertNotEquals(card1.getPoints().get(0), card2.getPoints().get(0));
        assertEquals(4, card1.getHighestPoint());
    }

    @Test
    @DisplayName("Test creations of the common cards for 3 players")
    public void testCreateCardsFor3Players() {
        int numOfPlayers = 3;
        List<CommonGoalCard> commonGoalCardList = CommonCardFactory.createCards(numOfPlayers);
        assertEquals(2, commonGoalCardList.size());
        CommonGoalCard card1 = commonGoalCardList.get(0);
        CommonGoalCard card2 = commonGoalCardList.get(1);
        assertNotEquals(card1, card2);

        int indexCard1 = card1.getIndex();
        int indexCard2 = card2.getIndex();
        assertNotEquals(indexCard1, indexCard2);

        for (int i = 0; i < 2; i++) {
            assertEquals(8, commonGoalCardList.get(i).getPoints().get(0));
            assertEquals(6, commonGoalCardList.get(i).getPoints().get(1));
            assertEquals(4, commonGoalCardList.get(i).getPoints().get(2));
        }

        int tmp = card1.getHighestPoint();
        assertEquals(8, tmp);
        assertNotEquals(card1.getPoints().get(0), card2.getPoints().get(0));
        assertEquals(6, card1.getHighestPoint());
        assertEquals(4, card1.getHighestPoint());
        assertEquals(8, card2.getHighestPoint());
    }

    @Test
    @DisplayName("Test creations of the common cards for 4 players")
    public void testCreateCardsFor4Players() {
        int numOfPlayers = 4;
        List<CommonGoalCard> commonGoalCardList = CommonCardFactory.createCards(numOfPlayers);
        assertEquals(2, commonGoalCardList.size());
        CommonGoalCard card1 = commonGoalCardList.get(0);
        CommonGoalCard card2 = commonGoalCardList.get(1);
        assertNotEquals(card1, card2);

        int indexCard1 = card1.getIndex();
        int indexCard2 = card2.getIndex();
        assertNotEquals(indexCard1, indexCard2);

        for (int i = 0; i < 2; i++) {
            assertEquals(8, commonGoalCardList.get(i).getPoints().get(0));
            assertEquals(6, commonGoalCardList.get(i).getPoints().get(1));
            assertEquals(4, commonGoalCardList.get(i).getPoints().get(2));
            assertEquals(2, commonGoalCardList.get(i).getPoints().get(3));
        }

        int tmp = card1.getHighestPoint();
        assertEquals(8, tmp);
        assertNotEquals(card1.getPoints().get(0), card2.getPoints().get(0));
        assertEquals(6, card1.getHighestPoint());
        assertEquals(4, card1.getHighestPoint());
        assertEquals(2, card1.getHighestPoint());
        assertEquals(8, card2.getHighestPoint());
    }
}