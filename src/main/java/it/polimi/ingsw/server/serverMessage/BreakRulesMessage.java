package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameMechanics.BreakRules;

public class BreakRulesMessage implements ServerMessage {

    private final BreakRules type;

    public BreakRulesMessage(BreakRules type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return type.getDescription();
    }
}