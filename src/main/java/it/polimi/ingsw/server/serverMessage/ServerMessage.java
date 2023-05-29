package it.polimi.ingsw.server.serverMessage;

import java.io.Serializable;

/**
 * This interface is used to send messages from the server to the client.
 */
public interface ServerMessage extends Serializable {
    /**
     * This method is used to get the message.
     *
     * @return the message
     */
    String getMessage();

    /**
     * This method implements the Visitor Pattern on ServerMessage
     * @param serverMessageHandler a class that implements ServerMessageHandler
     */
    void accept( ServerMessageHandler serverMessageHandler) ;

}
