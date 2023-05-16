package it.polimi.ingsw.view.events;

<<<<<<< HEAD:src/main/java/it/polimi/ingsw/client/clientMessage/UsernameChoice.java
/**
 * This class represents the message that the client sends when he chooses his username.
 */
public class UsernameChoice implements ClientMessage {
=======
import it.polimi.ingsw.client.clientMessage.ClientMessage;

public class UsernameChoice implements ClientMessage, ViewEvent {
>>>>>>> LorenzGUI2:src/main/java/it/polimi/ingsw/view/events/UsernameChoice.java

    /**
     * The username chosen by the client.
     */
    private final String username;

    /**
     * The constructor of the class.
     *
     * @param username the username chosen by the client
     */
    public UsernameChoice(String username) {
        this.username = username;
    }

    /**
     * Get the username chosen by the client.
     *
     * @return the username chosen by the client
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the message.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return null;
    }
<<<<<<< HEAD:src/main/java/it/polimi/ingsw/client/clientMessage/UsernameChoice.java
}
=======

    @Override
    public void accept(ViewChangeEventHandler viewChangeEventHandler) {
        viewChangeEventHandler.handle(this);
    }
}
>>>>>>> LorenzGUI2:src/main/java/it/polimi/ingsw/view/events/UsernameChoice.java
