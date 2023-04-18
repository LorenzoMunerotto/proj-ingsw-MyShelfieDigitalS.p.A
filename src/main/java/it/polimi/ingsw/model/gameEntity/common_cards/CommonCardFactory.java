package it.polimi.ingsw.model.gameEntity.common_cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A factory pattern for creating cards for a game.
 * This factory uses a random number generator to generate a set of common cards for the game.
 */
public class CommonCardFactory {

    /**
     * Number of total cards.
     */
    private static final int CARDS_NUMBER = 12;
    /**
     * Number of cards for the game.
     */
    private static final int CARDS_NUMBER_FOR_GAME = 2;
    /**
     * Random number generator.
     */
    private static final Random random = new Random();

    /**
     * Creates 2 random cards for the game.
     *
     * @return a list of 2 random cards
     */
    public static List<CommonGoalCard> createCards(){
        List<CommonGoalCard> commonCards = new ArrayList<>(CARDS_NUMBER_FOR_GAME);
        List<Integer> alreadyCreatedCards = new ArrayList<>(CARDS_NUMBER_FOR_GAME);

        for(int i = 0; i < CARDS_NUMBER_FOR_GAME; i++){
            int cardNumber = getRandomCardNumber(alreadyCreatedCards);
            alreadyCreatedCards.add(cardNumber);

            switch (cardNumber){
                case 1:
                    commonCards.add(new CommonCard1());
                    break;
                case 2:
                    commonCards.add(new CommonCard2());
                    break;
                case 3:
                    commonCards.add(new CommonCard3());
                    break;
                case 4:
                    commonCards.add(new CommonCard4());
                    break;
                case 5:
                    commonCards.add(new CommonCard5());
                    break;
                case 6:
                    commonCards.add(new CommonCard6());
                    break;
                case 7:
                    commonCards.add(new CommonCard7());
                    break;
                case 8:
                    commonCards.add(new CommonCard8());
                    break;
                case 9:
                    commonCards.add(new CommonCard9());
                    break;
                case 10:
                    commonCards.add(new CommonCard10());
                    break;
                case 11:
                    commonCards.add(new CommonCard11());
                    break;
                case 12:
                    commonCards.add(new CommonCard12());
                    break;
            }
        }
        return commonCards;
    }

    /**
     * Get a random number.
     * It checks if the number is already been extracted.
     *
     * @param usedCardNumbers is the list of extracted numbers
     * @return the new random number
     */
    private static int getRandomCardNumber(List<Integer> usedCardNumbers) {
        int cardNumber = random.nextInt(CARDS_NUMBER) + 1;
        while (usedCardNumbers.contains(cardNumber)) {
            cardNumber = random.nextInt(CARDS_NUMBER) + 1;
        }
        return cardNumber;
    }

    /**
     * This method is used for testing.
     *
     * @return a list of all the common cards
     */
    public static List<CommonGoalCard> getAllCommonCards(){
        List<CommonGoalCard> allCommonCards = new ArrayList<>(CARDS_NUMBER);
        allCommonCards.add(new CommonCard1());
        allCommonCards.add(new CommonCard2());
        allCommonCards.add(new CommonCard3());
        allCommonCards.add(new CommonCard4());
        allCommonCards.add(new CommonCard5());
        allCommonCards.add(new CommonCard6());
        allCommonCards.add(new CommonCard7());
        allCommonCards.add(new CommonCard8());
        allCommonCards.add(new CommonCard9());
        allCommonCards.add(new CommonCard10());
        allCommonCards.add(new CommonCard11());
        allCommonCards.add(new CommonCard12());
        return allCommonCards;
    }

}
