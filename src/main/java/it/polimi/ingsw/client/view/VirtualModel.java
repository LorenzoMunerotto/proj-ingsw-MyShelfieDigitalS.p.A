package it.polimi.ingsw.client.view;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;
import it.polimi.ingsw.model.gameState.GameData;

import java.util.List;
import java.util.Observable;

public interface VirtualModel {

    void initializeVirtualModel(GameData gameData);

    BoardCell[][] getBoard();
    ItemTile[][] getLibrary();
    ItemTile[][] getPersonalGoalCard();
    List<CommonGoalCard> getCommonGoalCards();
    List<Player> getPlayers();
    int getBag();

    Player getCurrentPlayer();

    void update(Observable o, Object arg);

    String getUsername();

    String getWinner();

    // at the begging I have a copy of the objects that I need to show to the user
    // when them get updated in the model I have to update them in the view through the controller
}
