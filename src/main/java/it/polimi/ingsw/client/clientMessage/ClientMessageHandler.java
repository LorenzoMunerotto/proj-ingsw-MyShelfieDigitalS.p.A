package it.polimi.ingsw.client.clientMessage;

import it.polimi.ingsw.view.events.Move;
import it.polimi.ingsw.view.events.NumOfPlayerChoice;
import it.polimi.ingsw.view.events.UsernameChoice;

/**
 * Interface used to handle the messages received from the server.
 */
public interface ClientMessageHandler {

    /**
     * Handles the message move.
     *
     * @param move the message move to handle.
     */
    void handle(Move move);
    /**
     * Handles the message numOfPlayerChoice.
     *
     * @param numOfPLayerChoice the message numOfPlayerChoice to handle.
     */
    void handle(NumOfPlayerChoice numOfPLayerChoice);


    /**
     * Handles the message usernameChoice.
     *
     * @param usernameChoice the message usernameChoice to handle.
     */
    void handle(UsernameChoice usernameChoice);

    /**
     * Handles the message checkConnection.
     *
     * @param checkConnection the message checkConnection to handle.
     */
    void handle(CheckConnection checkConnection);

    void handle(ChatClientMessage chatClientMessage);
}