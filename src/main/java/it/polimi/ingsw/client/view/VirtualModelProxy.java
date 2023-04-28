package it.polimi.ingsw.client.view;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameState.GameData;

import java.util.List;
import java.util.Observable;

public class VirtualModelProxy implements VirtualModel {

    private Player currentPlayer;
    private BoardCell[][] board;
    private ItemTile[][] userLibrary;
    private String username;
    private List<CommonGoalCard> commonGoalCards;
    private ItemTile[][] personalGoalCard;
    private List<Player> gamePlayers;
    private int gameBag;
    private final String winner = null;

    public VirtualModelProxy() {
    }
    @Override
    public void initializeVirtualModel(GameData gameData){

    }

    @Override
    public BoardCell[][] getBoard() {
        return this.board;
    }

    @Override
    public ItemTile[][] getLibrary() {
        return this.userLibrary;
    }

    @Override
    public ItemTile[][] getPersonalGoalCard() {
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
    public int getBag() {
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

    public void updateBoard (BoardCell[][] updatedBoard){
       this.board = updatedBoard;
    }

    public void updateLibrary (ItemTile[][] updatedLibrary){
        this.userLibrary = updatedLibrary;
    }

    public void updatePersonalGoalCard (ItemTile[][] updatedPersonalGoalCard){
        this.personalGoalCard = updatedPersonalGoalCard;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
