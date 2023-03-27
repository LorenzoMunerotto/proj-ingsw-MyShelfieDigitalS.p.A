package it.polimi.ingsw.model.logic;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.logic.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.data.enums.ItemTileType;

import java.util.List;
import java.util.stream.Collectors;

public class GameLogic {

    private final GameData gameData;

    //private final List<PersonalGoalCard> personalGoalCards;

    public GameLogic(List<Player> players, int numberOfPlayers){
        List<CommonGoalCard> commonGoalCards = CommonCardFactory.createCards(numberOfPlayers);
        List<String> playerUsernames = players.stream().map(Player::getUsername).collect(Collectors.toList());
        this.gameData = new GameData(playerUsernames, numberOfPlayers, commonGoalCards);
    }

    public GameData getGameData() {
        return this.gameData;
    }

    public void initGame(){
    }

    public void insertItemTile(int column, ItemTile itemTile, String username){
        ItemTile[][] libraryGrid = this.gameData.getPlayerDashboards().get(username).getLibrary().getGrid();
        if(column < 0 || column > libraryGrid[0].length - 1){
            throw new IllegalArgumentException("Invalid column index");
        }
        for(int row = libraryGrid.length - 1; row >= 0; row--){
            if(libraryGrid[row][column].getItemTileType() == ItemTileType.EMPTY){
                libraryGrid[row][column] = itemTile;
                return;
            }
        }
        throw new IllegalArgumentException("Column" + column + " is already full");
    }

    /*public void refillBoard(){
        Board gameBoard = this.gameData.getBoard();
        Bag gameBag = this.gameData.getBag();
        int emptyCells = gameBoard.getEmptyCells();
        List<ItemTile> itemTileList = gameBag.getRandomItemTiles(emptyCells);
        for(int row = 0; row < gameBoard.getBoardGrid().length; row++){
            for(int column = 0; column < gameBoard.getBoardGrid()[row].length; column++){
                if(gameBoard.getBoardGrid()[row][column] != null){
                    gameBoard.getBoardGrid()[row][column].setItemTile(itemTileList.remove(0));
                    gameBoard.setEmptyCells(gameBoard.getEmptyCells() - 1);
                }
            }
        }
    }*/

    public void updateBoard(){

    }

}
