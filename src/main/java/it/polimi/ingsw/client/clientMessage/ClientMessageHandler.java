package it.polimi.ingsw.client.clientMessage;

public interface ClientMessageHandler {

    void handle(Move move);
    void handle(NumOfPlayerChoice numOfPlayerChoice);
    void handle(UsernameChoice usernameChoice);
}
