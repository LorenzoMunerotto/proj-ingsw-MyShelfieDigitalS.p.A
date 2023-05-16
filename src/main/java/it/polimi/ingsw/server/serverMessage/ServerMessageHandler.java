package it.polimi.ingsw.server.serverMessage;

import java.io.IOException;

public interface ServerMessageHandler {

    void handle(BoardSetMessage boardSetMessage);
    void handle(BoardRefillMessage boardRefillMessage);
    void handle(BoardUpdateMessage boardUpdateMessage);
    void handle(BreakRulesMessage breakRulesMessage);
    void handle(CommonCardReachMessage commonCardReachMessage);
    void handle(CommonCardsSetMessage commonCardsSetMessage);
    void handle(ConnectionMessage connectionMessage);
    void handle(CustomMessage customMessage);
    void handle(DisconnectionMessage disconnectionMessage);
    void handle(EndGameMessage endGameMessage);
    void handle(ErrorMessage errorMessage);
    void handle(FirstFullLibraryMessage firstFullLibraryMessage);
    void handle(LibraryUpdateMessage libraryUpdateMessage);
    void handle(MoveRequest moveRequest);
    void handle(NumOfPlayerRequest numOfPlayerRequest);
    void handle(PersonalCardSetMessage personalCardSetMessage);
    void handle(PointsUpdateMessage pointsUpdateMessage);
    void handle(StartGameMessage startGameMessage) throws IOException;
    void handle(StartTurnMessage startTurnMessage);
    void handle(UsernameRequest usernameRequest);
    void handle(LibrarySetMessage librarySetMessage);

}