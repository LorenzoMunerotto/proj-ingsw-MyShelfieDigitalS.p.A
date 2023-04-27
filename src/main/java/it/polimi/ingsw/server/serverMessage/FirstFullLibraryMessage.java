package it.polimi.ingsw.server.serverMessage;

public class FirstFullLibraryMessage {

    private final String message;

    public FirstFullLibraryMessage(String username) {
        this.message = username + " is the first to fill the library, earns 1 point";
    }
}
