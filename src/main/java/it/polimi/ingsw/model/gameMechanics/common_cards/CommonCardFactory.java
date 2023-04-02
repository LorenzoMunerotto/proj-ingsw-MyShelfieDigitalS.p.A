package it.polimi.ingsw.model.gameMechanics.common_cards;

import java.util.ArrayList;
import java.util.Arrays;
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
     * @param numberOfPlayers is the number of players in the game
     * @return a list of 2 random cards
     */
    public static List<CommonGoalCard> createCards(int numberOfPlayers){
        List<CommonGoalCard> commonCards = new ArrayList<>(CARDS_NUMBER_FOR_GAME);
        List<Integer> alreadyCreatedCards = new ArrayList<>(CARDS_NUMBER_FOR_GAME);
        List<Integer> points = new ArrayList<>();

        switch (numberOfPlayers){
            case 2:
                points.addAll(Arrays.asList(8, 4));
                break;
            case 3:
                points.addAll(Arrays.asList(8, 6, 4));
                break;
            case 4:
                points.addAll(Arrays.asList(8, 6, 4, 2));
                break;
        }
        for(int i = 0; i < CARDS_NUMBER_FOR_GAME; i++){
            int cardNumber = getRandomCardNumber(alreadyCreatedCards);
            alreadyCreatedCards.add(cardNumber);
            List<Integer> cardPoints = new ArrayList<>(points);
            switch (cardNumber){
                case 1:
                    commonCards.add(new CommonCard1(cardNumber, cardPoints));
                    break;
                case 2:
                    commonCards.add(new CommonCard2(cardNumber, cardPoints));
                    break;
                case 3:
                    commonCards.add(new CommonCard3(cardNumber, cardPoints));
                    break;
                case 4:
                    commonCards.add(new CommonCard4(cardNumber, cardPoints));
                    break;
                case 5:
                    commonCards.add(new CommonCard5(cardNumber, cardPoints));
                    break;
                case 6:
                    commonCards.add(new CommonCard6(cardNumber, cardPoints));
                    break;
                case 7:
                    commonCards.add(new CommonCard7(cardNumber, cardPoints));
                    break;
                case 8:
                    commonCards.add(new CommonCard8(cardNumber, cardPoints));
                    break;
                case 9:
                    commonCards.add(new CommonCard9(cardNumber, cardPoints));
                    break;
                case 10:
                    commonCards.add(new CommonCard10(cardNumber, cardPoints));
                    break;
                case 11:
                    commonCards.add(new CommonCard11(cardNumber, cardPoints));
                    break;
                case 12:
                    commonCards.add(new CommonCard12(cardNumber, cardPoints));
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

}
