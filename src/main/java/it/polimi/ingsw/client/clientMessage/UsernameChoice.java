package it.polimi.ingsw.client.clientMessage;

public class UsernameChoice implements ClientMessage {

    private String username;

    public UsernameChoice(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
