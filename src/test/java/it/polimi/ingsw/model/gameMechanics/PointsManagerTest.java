package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.*;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;

import it.polimi.ingsw.model.gameState.Exceptions.IllegalUsernameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class PointsManagerTest {

    Player player;
    PointsManager pointsManager;
    LibraryTestHelper libraryTestHelper;

    //private Logger logger = Logger.getLogger(LibraryManagerTest.class.getName());

    @BeforeEach
    void setUp() throws IllegalUsernameException {

        player = new Player("giacomo");
        libraryTestHelper = new LibraryTestHelper();
        //sostituisco la libreria originale con il sottotipo che serve per fare i test
        player.setLibrary(libraryTestHelper);
    }

    @Test
    void commonPoints1()  {

        List<CommonGoalCard> commonGoalCardList = new ArrayList<>();
        commonGoalCardList.add(new CommonCard4());
        commonGoalCardList.add(new CommonCard11());
        Player player2 = null;
        try {
            player2 = new Player("anna");
        } catch (IllegalUsernameException e) {
            throw new RuntimeException(e);
        }
        commonGoalCardList.get(0).addSmartPlayer(player2);
        Optional<String> firstFullLibraryUsername = Optional.empty();
        Integer numOfPlayers = 3;

        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.PLANT, ItemTileType.PLANT, ItemTileType.PLANT},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.PLANT},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.PLANT, ItemTileType.GAME, ItemTileType.PLANT},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        pointsManager = new PointsManager(player,numOfPlayers,commonGoalCardList,firstFullLibraryUsername);

        assertEquals(14, pointsManager.commonPoints());
    }

    @Test
    void commonPoints2() {

        List<CommonGoalCard> commonGoalCardList = new ArrayList<>();
        commonGoalCardList.add(new CommonCard1());
        commonGoalCardList.add(new CommonCard12());
        Player player2 = null;
        Player player3 = null;
        try {
            player2 = new Player("anna");
            player3 = new Player("sara");
        } catch (IllegalUsernameException e) {
            throw new RuntimeException(e);
        }
        commonGoalCardList.get(0).addSmartPlayer(player2);
        commonGoalCardList.get(0).addSmartPlayer(player3);
        commonGoalCardList.get(1).addSmartPlayer(player);
        Optional<String> firstFullLibraryUsername = Optional.empty();
        Integer numOfPlayers = 3;

        ItemTileType[][] libraryGrid = {
                {ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.FRAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.FRAME, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.PLANT, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.TROPHY, ItemTileType.FRAME},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        pointsManager = new PointsManager(player,numOfPlayers,commonGoalCardList,firstFullLibraryUsername);

        assertEquals(12, pointsManager.commonPoints());



    }

    @Test
    void commonPoints3() {

        List<CommonGoalCard> commonGoalCardList = new ArrayList<>();
        commonGoalCardList.add(new CommonCard1());
        commonGoalCardList.add(new CommonCard12());
        commonGoalCardList.get(1).addSmartPlayer(player);
        Optional<String> firstFullLibraryUsername = Optional.empty();
        Integer numOfPlayers = 3;

        ItemTileType[][] libraryGrid = {
                {ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.FRAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.FRAME, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.PLANT, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.TROPHY, ItemTileType.FRAME},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        pointsManager = new PointsManager(player,numOfPlayers,commonGoalCardList,firstFullLibraryUsername);

        assertEquals(16, pointsManager.commonPoints());

    }

    @ParameterizedTest(name = "{displayName} - {index}")
    @CsvFileSource(resources = "/adjacentSource.csv")
    void adjacentPoints( String libraryAsString, Integer expectedPoints) {

        libraryTestHelper.setLibraryFromString(libraryAsString);

        /* It helps the debugging
        logger.info("Adjacent Item Tile Group List: "+libraryManager.getListGroupsAdjacentTiles().toString());
         */

        List<CommonGoalCard> commonGoalCardList = new ArrayList<>();
        Optional<String> firstFullLibraryUsername = Optional.empty();
        Integer numOfPlayers = 3;
        pointsManager = new PointsManager(player, numOfPlayers, commonGoalCardList, firstFullLibraryUsername);
        assertEquals(expectedPoints,pointsManager.adjacentPoints());

    }
}