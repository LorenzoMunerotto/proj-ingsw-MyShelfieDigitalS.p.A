package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameState.exceptions.BreakRules;

public class BreakRulesMessage implements ServerMessage {

    private final BreakRules type;

    public BreakRulesMessage(BreakRules type) {
        this.type = type;
    }

    public BreakRules getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return type.getDescription();
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
