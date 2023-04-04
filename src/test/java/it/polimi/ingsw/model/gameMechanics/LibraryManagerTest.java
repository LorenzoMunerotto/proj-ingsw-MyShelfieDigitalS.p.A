package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class LibraryManagerTest {


    LibraryTestHelper libraryTestHelper;
    LibraryManager libraryManager;
    private Logger logger = Logger.getLogger(LibraryManagerTest.class.getName());


    @BeforeEach
    void setUp(){
        libraryTestHelper = new LibraryTestHelper();
        libraryManager = new LibraryManager(libraryTestHelper);
    }

    @ParameterizedTest(name = "{displayName} - {index}")
    @CsvFileSource(resources = "/adjacentSource.csv")
    void adjacentPoints( String libraryAsString, Integer expectedPoints) {


        libraryTestHelper.setLibraryFromString(libraryAsString);
        /* in caso di errori facilita il debug
        logger.info("Adjacent Item Tile Group List: "+libraryManager.getListGroupsAdjacentTiles().toString());
         */
        assertEquals(expectedPoints,libraryManager.adjacentPoints());

    }
}