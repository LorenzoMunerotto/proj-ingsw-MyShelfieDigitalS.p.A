package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;

import java.io.Serializable;
import java.util.*;

/**
 * This abstract class is the superclass of the commonGoalCards.
 */
public abstract class CommonGoalCard implements Serializable {

    /**
     * The index of the CommonGoalCard
     */
    private final Integer index;
    /**
     * The description of the commonGoalCard.
     */
    private final String description;
    /**
     * The list of the usernames of the players who have reached the goal of
     * this commonGoalCard, in position 0 the first and then the others in order of achievement.
     */
    private final Map<String, Integer> achievedGoalPlayersMap;
    /**
     * The stack of the points of the CommonGoalCard.
     */
    private Stack<Integer> points;

    /**
     * Default constructor, initializes the commonGoalCard with the given index and description.
     *
     * @param index       is the index of the CommonGoalCard
     * @param description is the description of the CommonGoalCard
     */
    public CommonGoalCard(Integer index, String description) {
        this.index = index;
        this.description = description;
        this.points = new Stack<>();
        this.achievedGoalPlayersMap = new HashMap<>();
    }

    /**
     * This method adds the player to the list of the players who have reached the goal of this commonGoalCard.
     *
     * @param username is the username of the player
     */
    public void addAchievedGoalPlayer(String username) {
        if (!isAchievedGoalPlayer(username)) {
            achievedGoalPlayersMap.put(username, popPoint());
        }
    }

    /**
     * This method returns true if the player has achieved the goal of this commonGoalCard.
     *
     * @param username is the username of the player
     * @return true if the player has achieved the goal of this commonGoalCard, false otherwise
     */
    public boolean isAchievedGoalPlayer(String username) {
        return achievedGoalPlayersMap.containsKey(username);
    }

    /**
     * Set the points of the CommonGoalCard based on the number of players.
     *
     * @param numberOfPlayers is the number of players of the game
     */
    public void setPoints(int numberOfPlayers) {
        switch (numberOfPlayers) {
            case 2 -> {
                this.points.push(4);
                this.points.push(8);
            }
            case 3 -> {
                this.points.push(4);
                this.points.push(6);
                this.points.push(8);
            }
            case 4 -> {
                this.points.push(2);
                this.points.push(4);
                this.points.push(6);
                this.points.push(8);
            }
            default -> this.points = new Stack<>();
        }
    }

    /**
     * Pops the first element of the stack and returns it.
     *
     * @return the first element of the stack
     */
    public int popPoint() {
        try {
            return points.pop();
        } catch (EmptyStackException e) {
            return 0;
        }
    }

    /**
     * Get the top of the points stack.
     * The first available points earns by the next player who achieve the Goal
     *
     * @return the first element of the stack
     */
    public int topPoint() {
        try {
            return points.peek();
        } catch (EmptyStackException e) {
            return 0;
        }
    }

    /**
     * Get the map of the players who have reached the goal of this commonGoalCard.
     *
     * @return the map of the players who have reached the goal of this commonGoalCard
     */
    public Map<String, Integer> getAchievedGoalPlayersMap() {
        return this.achievedGoalPlayersMap;
    }

    /**
     * Get the index of the CommonGoalCard.
     *
     * @return the index of the CommonGoalCard
     */
    public Integer getIndex() {
        return index;
    }

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