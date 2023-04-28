package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.*;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;

import it.polimi.ingsw.model.gameEntity.personal_cards.CardsContainer;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointsManagerTest {

    Player player;
    PointsManager pointsManager;
    LibraryTestHelper library;
    CardsContainer cardsContainer;
    List<PersonalGoalCard> personalGoalCardList;

    @BeforeEach
    void setUp() {

        player = new Player("giacomo", 1);
        library = new LibraryTestHelper();
        player.setLibrary(library);
        cardsContainer = new CardsContainer();
        cardsContainer.createDeck();
        personalGoalCardList = cardsContainer.getAllPersonalGoalCards();
    }

    @Test
    void commonPoints1()  {

        List<CommonGoalCard> commonGoalCardList = new ArrayList<>();
        commonGoalCardList.add(new CommonCard4());
        commonGoalCardList.add(new CommonCard11());
        Player player2;
        player2 = new Player("anna", 1);
        commonGoalCardList.get(0).addSmartPlayer(player2);
        Integer numOfPlayers = 3;

        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.PLANT, ItemTileType.PLANT, ItemTileType.PLANT},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.PLANT},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.PLANT, ItemTileType.GAME, ItemTileType.PLANT},
        };
        library.setLibrary(libraryGrid);
        pointsManager = new PointsManager(player,numOfPlayers,commonGoalCardList);

        assertEquals(14, pointsManager.commonPoints());
    }

    @Test
    void commonPoints2() {

        List<CommonGoalCard> commonGoalCardList = new ArrayList<>();
        commonGoalCardList.add(new CommonCard1());
        commonGoalCardList.add(new CommonCard12());
        Player player2;
        Player player3;
        player2 = new Player("anna", 1);
        player3 = new Player("sara", 1);
        commonGoalCardList.get(0).addSmartPlayer(player2);
        commonGoalCardList.get(0).addSmartPlayer(player3);
        commonGoalCardList.get(1).addSmartPlayer(player);
        Integer numOfPlayers = 3;

        ItemTileType[][] libraryGrid = {
                {ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.FRAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.FRAME, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.PLANT, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.TROPHY, ItemTileType.FRAME},
        };
        library.setLibrary(libraryGrid);
        pointsManager = new PointsManager(player,numOfPlayers,commonGoalCardList);

        assertEquals(12, pointsManager.commonPoints());
    }

    @Test
    void commonPoints3() {

        List<CommonGoalCard> commonGoalCardList = new ArrayList<>();
        commonGoalCardList.add(new CommonCard1());
        commonGoalCardList.add(new CommonCard12());
        commonGoalCardList.get(1).addSmartPlayer(player);
        Integer numOfPlayers = 3;

        ItemTileType[][] libraryGrid = {
                {ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.FRAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.FRAME, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.PLANT, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.TROPHY, ItemTileType.FRAME},
        };
        library.setLibrary(libraryGrid);
        pointsManager = new PointsManager(player,numOfPlayers,commonGoalCardList);

        assertEquals(16, pointsManager.commonPoints());
    }

    @ParameterizedTest(name = "{displayName} - {index}")
    @CsvFileSource(resources = "/adjacentPointTest.csv")
    void adjacentPoints( String libraryAsString, Integer expectedPoints) {

        library.setLibraryFromString(libraryAsString);

        pointsManager = new PointsManager(player, 3, new ArrayList<>());
        assertEquals(expectedPoints,pointsManager.adjacentPoints());
    }

    @ParameterizedTest(name = "{displayName} - {index}")
    @CsvFileSource(resources = "/personalGoalCardsPointsTest.csv")
    public void personalGoalCardsPoints(String libraryAsString, Integer expectedPoints, Integer cardIndex) {
        library.setLibraryFromString(libraryAsString);
        player.setPersonalGoalCard(personalGoalCardList.get(cardIndex));

        pointsManager = new PointsManager(player, 3, new ArrayList<>());
        assertEquals(expectedPoints, pointsManager.personalPoints());
    }

    @ParameterizedTest(name = "{displayName} - {index}")
    @CsvFileSource(resources = "/totalPointsTest.csv")
    public void allPoints(String libraryAsString, Integer expectedPoints, Integer personalCardIndex, Integer commonCardIndex, Integer commonCardIndex2) {
        library.setLibraryFromString(libraryAsString);
        player.setPersonalGoalCard(personalGoalCardList.get(personalCardIndex));
        List<CommonGoalCard> commonGoalCardList = CommonCardFactory.getAllCommonCards();
        List<CommonGoalCard> twoCardsList = new ArrayList<>();
        twoCardsList.add(commonGoalCardList.get(commonCardIndex - 1));
        twoCardsList.add(commonGoalCardList.get(commonCardIndex2 - 1));

        pointsManager = new PointsManager(player, 3, twoCardsList);
        pointsManager.updateTotalPoints();
        assertEquals(expectedPoints, player.getTotPoints());

    }
}