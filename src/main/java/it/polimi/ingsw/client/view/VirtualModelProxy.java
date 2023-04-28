package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.view.cli.VirtualModel;
import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;
import it.polimi.ingsw.model.gameState.GameData;

import java.util.List;
import java.util.Observable;

public class VirtualModelProxy implements VirtualModel {

    private Player currentPlayer;
    private Board clientBoard;
    private Library userLibrary;
    private String username;
    private List<CommonGoalCard> commonGoalCards;
    private PersonalGoalCard personalGoalCard;
    private List<Player> gamePlayers;
    private Bag gameBag;
    private String winner = null;

    protected VirtualModelProxy() {

    }
    @Override
    public void initializeVirtualModel(GameData gameData){
        this.clientBoard = gameData.getBoard();
        this.userLibrary = gameData.getCurrentPlayer().getLibrary();
        this.username = gameData.getCurrentPlayer().getUsername();
        this.commonGoalCards = gameData.getCommonGoalCardsList();
        this.personalGoalCard = gameData.getCurrentPlayer().getPersonalGoalCard();
        this.gamePlayers = gameData.getPlayers();
    }

    @Override
    public Board getBoard() {
        return this.clientBoard;
    }

    @Override
    public Library getLibrary() {
        return this.userLibrary;
    }

    @Override
    public PersonalGoalCard getPersonalGoalCard() {
        return this.personalGoalCard;
    }

    @Override
    public List<CommonGoalCard> getCommonGoalCards() {
        return this.commonGoalCards;
    }

    @Override
    public List<Player> getPlayers() {
        return this.gamePlayers;
    }

    @Override
    public Bag getBag() {
        return this.gameBag;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getWinner() {
        return this.winner;
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void updateBoard (ItemTile[][] itemTiles){
        for(int i = 0; i < clientBoard.getROWS(); i++){
            for(int j = 0; j < clientBoard.getCOLUMNS(); j++){
                if(clientBoard.getBoardCell(i, j).isPlayable()){
                    this.clientBoard.putItemTile(i, j, itemTiles[i][j]);
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
