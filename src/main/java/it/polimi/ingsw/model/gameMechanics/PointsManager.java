package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameMechanics.common_cards.CommonGoalCard;

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


    public int personalPoints(){
        return 0;
    }

    public int adjacentPoints(){

        LibraryManager libraryManager = new LibraryManager(player.getLibrary());

      return libraryManager.adjacentPoints();

    }


    public int finalPoint(){

        if (firstFullLibraryUsername.isPresent()){
            if (firstFullLibraryUsername.get().equals(player.getUsername())) {
                return 1;
            }
        }
        return 0;
    }


    public void updateTotalPoints(){
        player.setTotPoints(commonPoints()+personalPoints()+adjacentPoints()+finalPoint());
    }

 
}
