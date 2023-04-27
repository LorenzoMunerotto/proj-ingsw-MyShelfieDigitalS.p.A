package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.AbstractListenable;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.personal_cards.Goal;
import it.polimi.ingsw.model.gameState.events.CommonCardReachEvent;
import it.polimi.ingsw.model.gameState.events.FirstFullLibraryEvent;
import it.polimi.ingsw.model.gameState.events.PointsUpdateEvent;
import org.javatuples.Pair;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Class that manages the points of the game.
 */
public class PointsManager extends AbstractListenable {
    /**
     * The player of the game.
     */
    private Player player;

    private final LibraryManager libraryManager;
    /**
     * The number of players of the game.
     */
    private Integer numOfPlayers;
    /**
     * The list of the common goal cards of the game.
     */
    private List<CommonGoalCard> commonGoalCardList;
    /**
     * The username of the first player that has completed the library.
     */
    private boolean thereIsFullLibrary;
    private Optional<String> firstFullLibraryUsername;


    public PointsManager(LibraryManager libraryManager) {
        this.libraryManager = libraryManager;
        this.thereIsFullLibrary =false;
        this.firstFullLibraryUsername=Optional.empty();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setNumOfPlayers(Integer numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    /**
     * Get the total points of the player, related to the common cards.
     *
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
                if(!commonGoalCard.isSmartPlayer(player) && commonGoalCard.checkRules(player.getLibrary())){
                    commonGoalCard.addSmartPlayer(player);
                    notifyAllListeners(new CommonCardReachEvent(player.getUsername(), commonGoalCard.getPoint(pointsSource, player), commonGoalCard.getIndex()));
                }
                if(commonGoalCard.isSmartPlayer(player)){
                    commonPoints+= commonGoalCard.getPoint(pointsSource, player);
                }
        }
        return commonPoints;
    }


    /**
     * Get the total points of the player, related to the personal card.
     *
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

    /**
     * Get the total points of the player, related to the adjacent items.
     *
     * @return total points achieved related to adjacent Groups
     */
    public int adjacentPoints(){
        Predicate<Pair<ItemTileType,Integer>> filterGroup =
                (group) -> (group.getValue0()!=ItemTileType.EMPTY && group.getValue1()>2);
        Function<Pair<ItemTileType,Integer>,Integer> calculateCommonPoints =
                (group)->{
                    int numberOfTile = group.getValue1();
                    if (numberOfTile==3) return 2;
                    else if (numberOfTile==4) return 3;
                    else if (numberOfTile==5) return 5;
                    else return 8;
                };


        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = libraryManager.getListGroupsAdjacentTiles();

         /* In caso di errori nei test per facilitare il debug
        System.out.println(listGroupsAdjacentTiles.stream().filter(filterGroup).collect(Collectors.toList()));
        System.out.println(listGroupsAdjacentTiles.stream().filter(filterGroup).map(calculateCommonPoints).collect(Collectors.toList()));
         */
        return listGroupsAdjacentTiles.stream().filter(filterGroup).map(calculateCommonPoints).reduce(0, Integer::sum);
    }

    /**
     * Get the final point of the player, if he is the first to complete the library.
     *
     * @return the additional point to the first player to the first finisher
     */
    public int finalPoint(){

        if (thereIsFullLibrary == false && libraryManager.isFull()){
            firstFullLibraryUsername = Optional.of(player.getUsername());
            notifyAllListeners(new FirstFullLibraryEvent(player.getUsername()));
            thereIsFullLibrary =true;
        }

        if (firstFullLibraryUsername.isEmpty()){
            return 0;
        }
        else{
            if (firstFullLibraryUsername.get().equals(player.getUsername())){
                return 1;
            }
            else{
                return 0;
            }
        }
    }

    /**
     * This method updates the Total Points adding Points related to: Common and Personal Card, adjacent Items, (optional) final point
     */
    public void updateTotalPoints() {
        Integer oldPoints = player.getTotPoints();
        Integer updatedPoints = commonPoints() + personalPoints() + adjacentPoints() + finalPoint();
        if (!oldPoints.equals(updatedPoints)) {
            player.setTotPoints(updatedPoints);
            notifyAllListeners(new PointsUpdateEvent(updatedPoints, player.getUsername()));
        }
    }

    public void setCommonGoalCardList(List<CommonGoalCard> commonGoalCardList) {
        this.commonGoalCardList = commonGoalCardList;
    }

    public boolean isThereFullLibrary() {
        return thereIsFullLibrary;
    }

    public Optional<String> getFirstFullLibraryUsername() {
        return firstFullLibraryUsername;
    }
}
