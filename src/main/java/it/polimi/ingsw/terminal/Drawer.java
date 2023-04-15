package it.polimi.ingsw.terminal;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.personal_cards.Goal;
import it.polimi.ingsw.model.gameState.GameData;

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

    public void drawGameInfo() {
        System.out.println("Common Goal Cards:");
        for (CommonGoalCard card : gameData.getCommonGoalCardsList()) {
            System.out.println("Card " + card.getIndex() + ": " + card.getDescription());
        }
        Board board = gameData.getBoard();
        System.out.println("\nGame Board: ");

        System.out.print("     ");
        for (int col = 0; col < board.getCOLUMNS(); col++) {
            System.out.printf(" %2d   ", col);
        }
        System.out.println();

        System.out.print("    ┌");
        for (int col = 0; col < board.getCOLUMNS() - 1; col++) {
            System.out.print("─────┬");
        }
        System.out.println("─────┐");

        for (int row = 0; row < board.getROWS(); row++) {
            System.out.printf(" %2d │", row);
            for (int col = 0; col < board.getCOLUMNS(); col++) {
                if (board.getBoardCell(row, col).isPlayable()) {
                    System.out.print(stringTile(board.getBoardCell(row, col).getItemTile().getItemTileType()));
                } else {
                    System.out.print("     ");
                }
                System.out.print("│");
            }
            System.out.println();

            if (row < board.getROWS() - 1) {
                System.out.print("    ├");
                for (int col = 0; col < board.getCOLUMNS() - 1; col++) {
                    System.out.print("─────┼");
                }
                System.out.println("─────┤");
            }
        }

        System.out.print("    └");
        for (int col = 0; col < board.getCOLUMNS() - 1; col++) {
            System.out.print("─────┴");
        }
        System.out.println("─────┘");

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void drawCurrentPlayerInfo() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Current player: " + gameData.getCurrentPlayer().getUsername());
        System.out.println("Points: " + gameData.getCurrentPlayer().getTotPoints());
        System.out.println("           Current Library:                  Personal Goal Card: ");
        Library goalLibrary = new Library();
        for (Goal goal : gameData.getCurrentPlayer().getPersonalGoalCard().getGoals()) {
            goalLibrary.setItemTile(goal.getRow(), goal.getColumn(), new ItemTile(goal.getItemTileType()));
        }
        drawLibraries(gameData.getCurrentPlayer().getLibrary(), goalLibrary);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

    }

    private String stringTile(ItemTileType itemTileType) {
        if (itemTileType == ItemTileType.CAT) return " CAT ";
        if (itemTileType == ItemTileType.BOOK) return " BOK ";
        if (itemTileType == ItemTileType.GAME) return " GAM ";
        if (itemTileType == ItemTileType.PLANT) return " PLA ";
        if (itemTileType == ItemTileType.TROPHY) return " TRO ";
        if (itemTileType == ItemTileType.FRAME) return " FRA ";
        return "     ";
    }

    public void drawLibraries(Library currentLibrary, Library personalCardLibrary) {
        System.out.print("     ");
        for (int col = 0; col < currentLibrary.getCOLUMNS(); col++) {
            System.out.print("  " + col + "   ");
        }
        System.out.print("     ");
        for (int col = 0; col < personalCardLibrary.getCOLUMNS(); col++) {
            System.out.print("  " + col + "   ");
        }
        System.out.println();

        System.out.print("    ┌");
        for (int col = 0; col < currentLibrary.getCOLUMNS() - 1; col++) {
            System.out.print("─────┬");
        }
        System.out.print("─────┐");
        System.out.print("    ┌");
        for (int col = 0; col < personalCardLibrary.getCOLUMNS() - 1; col++) {
            System.out.print("─────┬");
        }
        System.out.println("─────┐");

        for (int row = 0; row < currentLibrary.getROWS(); row++) {
            System.out.print("  " + row + " ");
            for (int col = 0; col < currentLibrary.getCOLUMNS(); col++) {
                System.out.print("│" + stringTile(currentLibrary.getItemTile(row, col).getItemTileType()));
            }
            System.out.print("│");
            System.out.print("  " + row + " ");
            for (int col = 0; col < personalCardLibrary.getCOLUMNS(); col++) {
                System.out.print("│" + stringTile(personalCardLibrary.getItemTile(row, col).getItemTileType()));
            }
            System.out.println("│");

            if (row < currentLibrary.getROWS() - 1) {
                System.out.print("    ├");
                for (int col = 0; col < currentLibrary.getCOLUMNS() - 1; col++) {
                    System.out.print("─────┼");
                }
                System.out.print("─────┤");
                System.out.print("    ├");
                for (int col = 0; col < personalCardLibrary.getCOLUMNS() - 1; col++) {
                    System.out.print("─────┼");
                }
                System.out.println("─────┤");
            }
        }

        System.out.print("    └");
        for (int col = 0; col < currentLibrary.getCOLUMNS() - 1; col++) {
            System.out.print("─────┴");
        }
        System.out.print("─────┘");
        System.out.print("    └");
        for (int col = 0; col < personalCardLibrary.getCOLUMNS() - 1; col++) {
            System.out.print("─────┴");
        }
        System.out.println("─────┘");
    }


    public void showRank() {
        List<Player> players = gameData.getPlayers().stream().sorted(Comparator.comparingInt(Player::getTotPoints).reversed()).collect(Collectors.toList());
        players.forEach(player -> System.out.println(player.getUsername() + " " + player.getTotPoints()));
    }
}
