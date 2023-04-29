package it.polimi.ingsw.client.view;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameState.GameData;

import java.util.List;

public interface VirtualModel {

    BoardCell[][] getBoard();
    ItemTile[][] getLibrary();
    ItemTile[][] getPersonalGoalCard();
    List<CommonGoalCard> getCommonGoalCards();
    List<Player> getPlayers();
    int getBag();
    Player getCurrentPlayer();
    String getUsername();

    String getWinner();

    // at the begging I have a copy of the objects that I need to show to the user
    // when them get updated in the model I have to update them in the view through the controller
}
