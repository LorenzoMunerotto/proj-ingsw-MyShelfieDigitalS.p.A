package it.polimi.ingsw.client.clientMessage;

/**
 * This class represent
 * the message that the client sends in response
 * to the server to demonstrate that it is still connected.
 */
public class CheckConnection  implements ClientMessage{

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(ClientMessageHandler clientMessageHandler) {
        clientMessageHandler.handle(this);
    }



}
