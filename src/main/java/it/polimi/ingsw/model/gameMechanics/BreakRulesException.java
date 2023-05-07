package it.polimi.ingsw.model.gameMechanics;

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
