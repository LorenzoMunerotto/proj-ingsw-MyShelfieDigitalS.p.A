package it.polimi.ingsw.server.serverMessage;

public class FirstFullLibraryMessage implements ServerMessage{

    private final String username;
    private final String message;

    public FirstFullLibraryMessage(String username) {
        this.username = username;
        this.message = username + " is the first to fill the library, earns 1 point";
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getMessage() {
        return message;
    }


    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
