package it.polimi.ingsw.client.clientMessage;



public class CheckConnection  implements ClientMessage{

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void accept(ClientMessageHandler clientMessageHandler) {
        clientMessageHandler.handle(this);
    }



}
