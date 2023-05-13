package it.polimi.ingsw.model.gameState.exceptions;

public class BreakRulesException extends Exception {

    private final BreakRules type;

    public BreakRulesException(BreakRules type) {
        this.type = type;
    }

    public BreakRules getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return type.getDescription();
    }
}
