package it.polimi.ingsw.terminal;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.personal_cards.Goal;
import it.polimi.ingsw.model.gameState.GameData;
import it.polimi.ingsw.view.cli.CLIColors;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that draws the game info on the terminal.
 */
public class Drawer {

    final GameData gameData;

    public Drawer(GameData gameData) {
        this.gameData = gameData;
    }

    private String stringTile(ItemTileType itemTileType) {
        if (itemTileType == ItemTileType.CAT) return CLIColors.GREEN_BRIGHT + " CAT " + CLIColors.RESET;
        if (itemTileType == ItemTileType.BOOK) return CLIColors.WHITE_BRIGHT + " BOO " + CLIColors.RESET;
        if (itemTileType == ItemTileType.GAME) return CLIColors.YELLOW_BRIGHT + " GAM " + CLIColors.RESET;
        if (itemTileType == ItemTileType.FRAME) return CLIColors.BLUE_BRIGHT + " FRA " + CLIColors.RESET;
        if (itemTileType == ItemTileType.TROPHY) return CLIColors.CYAN_BRIGHT + " TRO " + CLIColors.RESET;
        if (itemTileType == ItemTileType.PLANT) return CLIColors.PURPLE_BRIGHT + " PLA " + CLIColors.RESET;
        return "     ";
    }

    public void showRank() {
        List<Player> players = gameData.getPlayers().stream().sorted(Comparator.comparingInt(Player::getTotPoints).reversed()).collect(Collectors.toList());
        players.forEach(player -> System.out.println(player.getUsername() + " " + player.getTotPoints()));
    }
}
