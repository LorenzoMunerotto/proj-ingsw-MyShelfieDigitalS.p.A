package it.polimi.ingsw.client.clientMessage;

import java.io.Serializable;

/**
 * Interface that represents a message sent by the client to the server.
 */
public interface ClientMessage extends Serializable {
    /**
     * Get the message.
     *
     * @return the message
     */
    String getMessage();
}