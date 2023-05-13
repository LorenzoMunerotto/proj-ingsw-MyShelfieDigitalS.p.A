package it.polimi.ingsw.client.clientMessage;

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
     * @param numberOfPLayerChoice the message numOfPlayerChoice to handle.
     */
    void handle(NumberOfPLayerChoice numberOfPLayerChoice);

    /**
     * Handles the message usernameChoice.
     *
     * @param usernameChoice the message usernameChoice to handle.
     */
    void handle(UsernameChoice usernameChoice);
}