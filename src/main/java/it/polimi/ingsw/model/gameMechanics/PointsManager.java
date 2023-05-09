package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.listener.AbstractListenable;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.personal_cards.Goal;
import it.polimi.ingsw.model.gameState.events.CommonCardReachEvent;
import it.polimi.ingsw.model.gameState.events.FirstFullLibraryEvent;
import it.polimi.ingsw.model.gameState.events.PointsUpdateEvent;
import org.javatuples.Pair;

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
    /**
     * The list of the common goal cards of the game.
     */
    private List<CommonGoalCard> commonGoalCardList;
    /**
     * The username of the first player that has completed the library.
     */
    private Optional<String> firstFullLibraryUsername;
    /**
     * True if the first player that has completed the library is present, false otherwise.
     */
    private boolean isPresentFirstFullLibraryUsername;

    public PointsManager() {
        this.firstFullLibraryUsername = Optional.empty();
        this.isPresentFirstFullLibraryUsername = false;
    }

    /**
     * Get the total points of the player, related to the common cards.
     *
     * @return total points achieved related to Common Cards
     */
    public int commonPoints() {
        int commonPoints = 0;
        for (CommonGoalCard commonGoalCard : commonGoalCardList) {
            if(commonGoalCard.isAchievedGoalPlayer(player.getUsername())) commonPoints += commonGoalCard.getAchievedGoalPlayersMap().get(player.getUsername());
            else if(!commonGoalCard.isAchievedGoalPlayer(player.getUsername()) && commonGoalCard.checkRules(player.getLibrary())){
                commonGoalCard.addAchievedGoalPlayer(player.getUsername());
                commonPoints += commonGoalCard.getAchievedGoalPlayersMap().get(player.getUsername());
                notifyAllListeners(new CommonCardReachEvent(player.getUsername(), commonGoalCard.getAchievedGoalPlayersMap().get(player.getUsername()), commonGoalCard.topPoint(), commonGoalCard.getIndex()));
            }
        }
        return commonPoints;
    }

    /**
     * Get the total points of the player, related to the personal card.
     *
     * @return total points achieved related to Common Cards
     */
    public int personalPoints() {

        int counter = 0;

        List<Goal> goals = player.getPersonalGoalCard().getGoals();
        for (Goal goal : goals) {
            int row = goal.getRow();
            int col = goal.getColumn();

            if (player.getLibrary().getItemTile(row, col) == goal.getItemTileType()) {
                counter++;
            }
        }
        return switch (counter) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 6;
            case 5 -> 9;
            case 6 -> 12;
            default -> 0;
        };
    }

    /**
     * Get the total points of the player, related to the adjacent items.
     *
     * @return total points achieved related to adjacent Groups
     */
    public int adjacentPoints() {
        Predicate<Pair<ItemTileType, Integer>> filterGroup =
                (group) -> (group.getValue0() != ItemTileType.EMPTY && group.getValue1() > 2);
        Function<Pair<ItemTileType, Integer>, Integer> calculateCommonPoints =
                (group) -> {
                    int numberOfTile = group.getValue1();
                    if (numberOfTile == 3) return 2;
                    else if (numberOfTile == 4) return 3;
                    else if (numberOfTile == 5) return 5;
                    else return 8;
                };
        LibraryManager libraryManager = new LibraryManager();
        libraryManager.setLibrary(player.getLibrary());
        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = libraryManager.getListGroupsAdjacentTiles();

        return listGroupsAdjacentTiles.stream().filter(filterGroup).map(calculateCommonPoints).reduce(0, Integer::sum);
    }

    /**
     * Get the final point of the player, if he is the first to complete the library.
     *
     * @return the additional point to the first player to the first finisher
     */
    public int finalPoint() {
        if (firstFullLibraryUsername.isPresent()) {
            if (firstFullLibraryUsername.get().equals(player.getUsername())) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * This method updates the Total Points adding Points related to: Common and Personal Card, adjacent Items, (optional) final point
     */
    public void updateTotalPoints() {
        int oldPoints = player.getTotPoints();
        int updatedPoints = commonPoints() + personalPoints() + adjacentPoints() + finalPoint();
        if (oldPoints != updatedPoints) {
            player.setTotPoints(updatedPoints);
            notifyAllListeners(new PointsUpdateEvent(updatedPoints, player.getUsername()));
        }
    }

    public boolean isPresentFirstFullLibraryUsername() {
        return isPresentFirstFullLibraryUsername;
    }

    public void setPresentFirstFullLibraryUsername(boolean presentFirstFullLibraryUsername) {
        isPresentFirstFullLibraryUsername = presentFirstFullLibraryUsername;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCommonGoalCardList(List<CommonGoalCard> commonGoalCardList) {
        this.commonGoalCardList = commonGoalCardList;
    }

    public void setFirstFullLibraryUsername(Optional<String> firstFullLibraryUsername) {
        this.firstFullLibraryUsername = firstFullLibraryUsername;
        firstFullLibraryUsername.ifPresent(s -> notifyAllListeners(new FirstFullLibraryEvent(s)));
    }
}
