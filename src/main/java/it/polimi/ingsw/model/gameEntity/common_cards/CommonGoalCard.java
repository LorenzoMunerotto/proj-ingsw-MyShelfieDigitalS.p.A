package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.Player;

import java.util.ArrayList;
import java.util.List;


public abstract class CommonGoalCard {

    /**
     * is the index of the CommonGoalCard
     */
    private Integer index;
    //private String description;

    /**
     * is the list of the usernames of the players who have reached the goal of
     * this commonGoalCard, in position 0 the first and then the others in order
     * of achievement.
     */
    private List<String> smartPlayersTail;

    public CommonGoalCard(Integer index) {
        this.index = index;
        this.smartPlayersTail =  new ArrayList<>();
    }

    /**
     * This method add the player's username to the smartPlayersTail
     * @param player
     */
    public void addSmartPlayer(Player player){
        if(!isSmartPlayer(player)) {
            smartPlayersTail.add(player.getUsername());
        }
    }

    /**
     * This method return true if the player has already reached the goal of this commonGoalCard
     * @param player
     */
    public boolean isSmartPlayer(Player player){
        return smartPlayersTail.indexOf(player.getUsername())!=-1;
    }


    /**
     * this method provides the points that a player, who has achieved the goal,
     * has earned based on the order of achievement and the number of players
     * in the game
     *
     * @param pointsSource the list of integer with points, depends on numOfPlayers
     * @param player
     * @return the points earned by the player based on the order of achievement of the goal
     */
    public int getPoint(List<Integer> pointsSource, Player player){
        int point = pointsSource.get(smartPlayersTail.indexOf(player.getUsername()));
        return point;
    }

    public Integer getIndex()  {return index;}

    public abstract boolean checkRules(Library library);
}
