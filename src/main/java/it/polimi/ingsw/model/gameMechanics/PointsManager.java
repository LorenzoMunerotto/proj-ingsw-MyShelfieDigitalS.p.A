package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.personal_cards.Goal;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PointsManager {
    private Player player;
    private Integer numOfPlayers;
    private List<CommonGoalCard> commonGoalCardList;

    private Optional<String> firstFullLibraryUsername;

    public PointsManager(Player currentPlayer, Integer numOfPlayers, List<CommonGoalCard> commonGoalCardList, Optional<String> firstFullLibraryUsername) {
        this.player = currentPlayer;
        this.numOfPlayers = numOfPlayers;
        this.commonGoalCardList = commonGoalCardList;
        this.firstFullLibraryUsername = firstFullLibraryUsername;
    }

    
    /**
     * @return total points achieved related to Common Cards
     */
    public int commonPoints() {
        int commonPoints =0;

        List<Integer> pointsSource = new ArrayList<>();
        switch (numOfPlayers){
            case 2:
                pointsSource = Arrays.asList(8,4);
                break;
            case 3:
                pointsSource = Arrays.asList(8,6,4);
                break;
            case 4:
                pointsSource = Arrays.asList(8,6,4,2);
                break;
        }


        for (CommonGoalCard commonGoalCard : commonGoalCardList){
                if(!commonGoalCard.isSmartPlayer(player.getUsername()) && commonGoalCard.checkRules(player.getLibrary())){
                    commonGoalCard.addSmartPlayer(player.getUsername());
                }
                if(commonGoalCard.isSmartPlayer(player.getUsername())){
                    commonPoints+= commonGoalCard.getPoint(pointsSource, player.getUsername());
                }
        }

        return commonPoints;
    }


    /**
     * @return total points achieved related to Common Cards
     */
    public int personalPoints(){


        List<Goal> goals = player.getPersonalGoalCard().getGoals();

        int counter =0;
        for (Goal goal : goals) {
            int row = goal.getRow();
            int col = goal.getColumn();


            if (player.getLibrary().getItemTile(row, col).getItemTileType() == ItemTileType.valueOf(goal.getItemTileType())) {
                counter++;
            }
        }

            switch (counter){

                case 1:  return 1;
                case 2:  return 2;
                case 3:  return 4;
                case 4:  return 6;
                case 5:  return 9;
                case 6:  return 12;

            }


        return 0;

    }

    public int adjacentPoints(){

        LibraryManager libraryManager = new LibraryManager(player.getLibrary());

      return libraryManager.adjacentPoints();

    }

    /**
     * @return the additional point to the first player to the first finisher
     */
    public int finalPoint(){


        if (firstFullLibraryUsername.isPresent()){
            if (firstFullLibraryUsername.get().equals(player.getUsername())) {
                return 1;
            }
        }

        return 0;
    }

    /**
     * update Total Points adding Points related to: Common and Personal Card, adjacent Items, (optional) final point
     */
    public void updateTotalPoints(){
        player.setTotPoints(commonPoints()+personalPoints()+adjacentPoints()+finalPoint());
    }

 
}
