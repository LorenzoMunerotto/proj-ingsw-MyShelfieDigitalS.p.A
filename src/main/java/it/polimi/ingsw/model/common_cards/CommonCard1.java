package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;

public class CommonCard1 implements CommonGoalCard{

    @Override
    public boolean checkRules(GameData gameData, String name) {
        return false;
    }
}
