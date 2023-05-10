package it.polimi.ingsw.client.clientMessage;

import it.polimi.ingsw.view.events.Move;
import it.polimi.ingsw.view.events.NumOfPlayerChoice;
import it.polimi.ingsw.view.events.UsernameChoice;

public interface ClientMessageHandler {

    void handle(Move move);
    void handle(NumOfPlayerChoice numOfPlayerChoice);
    void handle(UsernameChoice usernameChoice);
}
