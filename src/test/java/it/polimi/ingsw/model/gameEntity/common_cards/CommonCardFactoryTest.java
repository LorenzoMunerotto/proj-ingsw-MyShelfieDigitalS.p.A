package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCardFactoryTest {

    @Test
    @DisplayName("Test creations of the common cards")
    public void testCreateCardsFor2Players() {

        List<CommonGoalCard> commonGoalCardList = CommonCardFactory.createCards();
        assertEquals(2, commonGoalCardList.size());

        CommonGoalCard card1 = commonGoalCardList.get(0);
        CommonGoalCard card2 = commonGoalCardList.get(1);
        assertNotEquals(card1, card2);

        int indexCard1 = card1.getIndex();
        int indexCard2 = card2.getIndex();
        assertNotEquals(indexCard1, indexCard2);

    }

}
