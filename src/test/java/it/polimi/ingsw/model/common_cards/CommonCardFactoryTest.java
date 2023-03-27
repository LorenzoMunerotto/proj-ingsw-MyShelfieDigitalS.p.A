package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.logic.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCardFactoryTest {

    @Test
    @DisplayName("Test creations of the common cards")
    public void testCreateCards() {
        int numOfPlayers = 2;
        List<CommonGoalCard> commonGoalCardList = CommonCardFactory.createCards(numOfPlayers);
        assertEquals(numOfPlayers, commonGoalCardList.size());

        CommonGoalCard card1 = commonGoalCardList.get(0);
        CommonGoalCard card2 = commonGoalCardList.get(1);
        assertNotEquals(card1, card2);

        int indexCard1 = card1.getIndex();
        int indexCard2 = card2.getIndex();
        assertNotEquals(indexCard1, indexCard2);

        int points1 = card1.getPoints().get(0);
        assertEquals(8, points1);

        int points2 = card1.getPoints().get(1);
        assertEquals(6, points2);
    }
}