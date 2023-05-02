package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public abstract class CommonGoalCard implements Serializable {

    /**
     * is the index of the CommonGoalCard
     */
    private final Integer index;
    private String description;

    /**
     * The list of the usernames of the players who have reached the goal of
     * this commonGoalCard, in position 0 the first and then the others in order of achievement.
     */
    private final List<String> smartPlayersTail;

    public CommonGoalCard(Integer index, String description) {
        this.index = index;
        this.description = description;
        this.smartPlayersTail =  new ArrayList<>();
    }

    /**
     * This method add the player's username to the smartPlayersTail.
     *
     * @param player is the player to add
     */
    public void addSmartPlayer(Player player){
        if(!isSmartPlayer(player)) {
            smartPlayersTail.add(player.getUsername());
        }
    }

    /**
     * This method return true if the player has already reached the goal of this commonGoalCard.
     *
     * @param player is the player to check
     */
    public boolean isSmartPlayer(Player player){
        return smartPlayersTail.contains(player.getUsername());
    }


    /**
     * This method provides the points that a player, who has achieved the goal,
     * has earned based on the order of achievement and the number of players in the game.
     *
     * @param pointsSource the list of integer with points, depends on numOfPlayers
     * @param player is the player who has achieved the goal
     * @return the points earned by the player based on the order of achievement of the goal
     */
    public int getPoint(List<Integer> pointsSource, Player player){
        return pointsSource.get(smartPlayersTail.indexOf(player.getUsername()));
    }

    /**
     * Get the index of the CommonGoalCard.
     *
     * @return the index of the CommonGoalCard
     */
    public Integer getIndex()  {return index;}

    /**
     * Get the description of the CommonGoalCard.
     *
     * @return the description of the CommonGoalCard
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method checks if the player has reached the goal of this commonGoalCard
     *
     * @return true if the player has reached the goal of this commonGoalCard
     */
    public abstract boolean checkRules(Library library);
}
