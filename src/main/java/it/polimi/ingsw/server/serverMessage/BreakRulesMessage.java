package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameState.exceptions.BreakRules;

/**
 * This class represent the message which is sent to a player when his move
 * break the game rule
 */
public class BreakRulesMessage implements ServerMessage {

    /**
     * The broken rule
     */
    private final BreakRules type;

    public BreakRulesMessage(BreakRules type) {
        this.type = type;
    }

    /**
     * Get the broken rule
     * @return type
     */
    public BreakRules getType() {
        return type;
    }

    /**
     *{inherit}
     */
    @Override
    public String getMessage() {
        return type.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
