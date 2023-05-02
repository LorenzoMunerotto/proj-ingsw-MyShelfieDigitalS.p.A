package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameState.events.PersonalCardSetEvent;
import org.sonatype.guice.plexus.config.Strategies;

public interface ServerMessageHandler {


    void handle(BoardRefillMessage boardRefillMessage);
    void handle(BoardUpdateMessage boardUpdateMessage);
    void handle(BreakRulesMessage breakRulesMessage);
    void handle(CommonCardReachMessage commonCardReachMessage);
    void handle(CommonCardsSetMessage commonCardsSetMessage);
    void handle(ConnectionMessage connectionMessage);
    void handle(CustomMessage customMessage);
    void handle(EndGameMessage endGameMessage);
    void handle(EndTurnMessage endTurnMessage);
    void handle(ErrorMessage errorMessage);
    void handle(FirstFullLibraryMessage firstFullLibraryMessage);
    void handle(LibraryUpdateMessage libraryUpdateMessage);
    void handle(MoveRequest moveRequest);
    void handle(NumOfPlayerRequest numOfPlayerRequest);
    void handle(PersonalCardSetMessage personalCardSetMessage);
    void handle(PointsUpdateMessage pointsUpdateMessage);
    void handle(StartGameMessage startGameMessage);
    void handle(StartTurnMessage startTurnMessage);
    void handle(UsernameRequest usernameRequest);

}
