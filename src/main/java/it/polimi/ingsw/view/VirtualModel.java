package it.polimi.ingsw.view;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;
import it.polimi.ingsw.model.gameState.GameData;

import java.util.List;
import java.util.Map;

public class VirtualModel {

    private final GameData gameData;
    private Board clientBoard;
    private Library userLibrary;
    private String username;
    private List<CommonGoalCard> commonGoalCards;
    private PersonalGoalCard personalGoalCard;

    public VirtualModel(GameData gameData) {
        this.gameData = gameData;
    }

    public GameData getGameData() {
        return gameData;
    }
}
