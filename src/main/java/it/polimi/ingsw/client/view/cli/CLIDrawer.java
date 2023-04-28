package it.polimi.ingsw.client.view.cli;

import it.polimi.ingsw.client.view.VirtualModel;
import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;

import java.util.Comparator;
import java.util.List;

public class CLIDrawer {
    private final VirtualModel virtualModel;

    public CLIDrawer(VirtualModel virtualModel) {
        this.virtualModel = virtualModel;
    }

    public void printGame() {
        printGameObjects();
        printCommonGoalCards();
        printSeparator();
        printLeaderBoard();
    }

    protected void printSeparator() {
        System.out.printf("%s%s%s%n", CLIColors.WHITE_BRIGHT, "-".repeat(300), CLIColors.RESET);
    }

    /**
     * Print the board and the two libraries parallel to each other.
     */
    public void printGameObjects() {
        BoardCell[][] board = virtualModel.getBoard();
        ItemTile[][] currentLibrary = virtualModel.getLibrary();
        ItemTile[][] personalCardLibrary = virtualModel.getPersonalGoalCard();

        System.out.println(CLIAssets.PRINT_OBJECTS_TITLE);
        printFirstRow(board, currentLibrary, personalCardLibrary);
        printfTopLine(board, currentLibrary, personalCardLibrary);

        for (int row = 0; row < board.length; row++) {
            printBoardCells(board, row);
            if (row < currentLibrary.length && row < personalCardLibrary.length) {
                System.out.print("     ");
                printLibraryCells(currentLibrary, row, true);
                System.out.print("  ");
                printLibraryCells(personalCardLibrary, row, false);
            } else {
                System.out.println();
            }
            printMiddleLine(board, currentLibrary, personalCardLibrary, row);
        }
        printBottomLine(board);
    }

    /**
     * Print the first row of the board and the two libraries.
     *
     * @param board               is the board to print
     * @param currentLibrary      is the current library to print
     * @param personalCardLibrary is the library based on the personal goal card to print
     */
    private void printFirstRow(BoardCell[][] board, ItemTile[][] currentLibrary, ItemTile[][] personalCardLibrary) {
        StringBuilder sb = new StringBuilder();

        sb.append("     ");
        for (int col = 0; col < board.length; col++) {
            sb.append(String.format("  %s   ", CLIAssets.CARDINALITY_MAP_COLUMN.get(col)));
        }
        sb.append("          ");
        for (int col = 0; col < currentLibrary[0].length; col++) {
            sb.append(String.format("  %s   ", CLIAssets.CARDINALITY_MAP_COLUMN.get(col)));
        }
        sb.append("       ");
        for (int col = 0; col < personalCardLibrary[0].length; col++) {
            sb.append(String.format("  %s   ", CLIAssets.CARDINALITY_MAP_COLUMN.get(col)));
        }
        System.out.println(sb);
    }

    /**
     * Print the top line of the board and the two libraries.
     *
     * @param board               is the board to print
     * @param currentLibrary      is the current library to print
     * @param personalCardLibrary is the library based on the personal goal card to print
     */
    private void printfTopLine(BoardCell[][] board, ItemTile[][] currentLibrary, ItemTile[][] personalCardLibrary) {
        printDelimiterLine(board.length, "    ");
        printDelimiterLine(currentLibrary[0].length, "         ");
        printDelimiterLine(personalCardLibrary[0].length, "      ");
        System.out.println();
    }

    /**
     * Print the delimiter line of the board and the two libraries.
     *
     * @param numColumns is the number of columns of the board or the library
     * @param offset     is the offset to apply to the line
     */
    private void printDelimiterLine(int numColumns, String offset) {
        StringBuilder sb = new StringBuilder();
        sb.append(offset).append(CLIColors.YELLOW_BOLD).append("┌").append(CLIColors.RESET);

        for (int col = 0; col < numColumns - 1; col++) {
            sb.append(CLIColors.YELLOW_BOLD).append("─────┬").append(CLIColors.RESET);
        }
        sb.append(CLIColors.YELLOW_BOLD).append("─────┐").append(CLIColors.RESET);

        System.out.print(sb);
    }

    /**
     * Print the middle line of the board and the two libraries.
     *
     * @param board               is the board to print
     * @param currentLibrary      is the current library to print
     * @param personalCardLibrary is the library based on the personal goal card to print
     * @param row                 is the current row
     */
    private void printMiddleLine(BoardCell[][] board, ItemTile[][] currentLibrary, ItemTile[][] personalCardLibrary, int row) {
        if (row < currentLibrary.length - 1) {
            System.out.print("    ");
            for (int col = 0; col < board.length; col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "├─────" + CLIColors.RESET);
            }
            System.out.print(CLIColors.YELLOW_BOLD + "┤         " + CLIColors.RESET);
            for (int col = 0; col < currentLibrary[0].length; col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "├─────" + CLIColors.RESET);
            }
            System.out.print(CLIColors.YELLOW_BOLD + "┤      " + CLIColors.RESET);
            for (int col = 0; col < personalCardLibrary[0].length; col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "├─────" + CLIColors.RESET);
            }
            System.out.println(CLIColors.YELLOW_BOLD + "┤" + CLIColors.RESET);
        } else if (row == currentLibrary.length - 1) {
            System.out.print("    ");
            for (int col = 0; col < board.length; col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "├─────" + CLIColors.RESET);
            }
            System.out.print(CLIColors.YELLOW_BOLD + "┤         " + CLIColors.RESET);
            for (int col = 0; col < currentLibrary[0].length; col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "└─────" + CLIColors.RESET);
            }
            System.out.print(CLIColors.YELLOW_BOLD + "┘      " + CLIColors.RESET);
            for (int col = 0; col < personalCardLibrary[0].length; col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "└─────" + CLIColors.RESET);
            }
            System.out.println(CLIColors.YELLOW_BOLD + "┘" + CLIColors.RESET);
        } else if (row < board.length - 1) {
            System.out.print("    ");
            for (int col = 0; col < board.length; col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "├─────" + CLIColors.RESET);
            }
            System.out.println(CLIColors.YELLOW_BOLD + "┤" + CLIColors.RESET);

        }
    }

    /**
     * Print the bottom line of the board.
     *
     * @param board is the board to print
     */
    private void printBottomLine(BoardCell[][] board) {
        System.out.print(CLIColors.YELLOW_BOLD + "    └" + CLIColors.RESET);
        for (int col = 0; col < board.length - 1; col++) {
            System.out.print(CLIColors.YELLOW_BOLD + "─────┴" + CLIColors.RESET);
        }
        System.out.println(CLIColors.YELLOW_BOLD + "─────┘" + CLIColors.RESET);
    }

    /**
     * Print the board cells.
     *
     * @param board is the board to print
     * @param row   is the current row
     */
    private void printBoardCells(BoardCell[][] board, int row) {
        String rowLetter = CLIAssets.CARDINALITY_MAP_ROW.get(row);
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(" %s  %s│%s", rowLetter, CLIColors.YELLOW_BOLD, CLIColors.RESET));
        for (int col = 0; col < board.length; col++) {
            if (board[row][col].isPlayable()) {
                sb.append(CLIAssets.ITEM_TILES_TYPES_CLI_COLORS.get((board[row][col].getItemTile().getItemTileType())));
            } else {
                sb.append(CLIColors.BLUE_BACKGROUND_BRIGHT + "     " + CLIColors.RESET);
            }
            sb.append(CLIColors.YELLOW_BOLD + "│" + CLIColors.RESET);
        }

        System.out.print(sb);
    }

    /**
     * Print the current library cells.
     *
     * @param library          is the library to print
     * @param row              is the current row
     * @param isCurrentLibrary is true if the library is the current library, false otherwise
     */
    private void printLibraryCells(ItemTile[][] library, int row, boolean isCurrentLibrary) {
        StringBuilder sb = new StringBuilder();

        sb.append("  ").append(CLIAssets.CARDINALITY_MAP_ROW.get(row)).append(" ");
        for (int col = 0; col < library[0].length; col++) {
            sb.append(CLIColors.YELLOW_BOLD + "│" + CLIColors.RESET).append(CLIAssets.ITEM_TILES_TYPES_CLI_COLORS.get(library[row][col].getItemTileType()));
        }
        sb.append(CLIColors.YELLOW_BOLD + "│" + CLIColors.RESET);

        if (isCurrentLibrary) {
            System.out.print(sb);
        } else {
            System.out.println(sb);
        }
    }

    /**
     * Print the common goal cards.
     */
    private void printCommonGoalCards() {
        List<CommonGoalCard> commonGoalCards = virtualModel.getCommonGoalCards();

        int maxLineLength = 0;
        for (CommonGoalCard card : commonGoalCards) {
            String description = card.getDescription();
            String[] descriptionLines = description.split("\n");
            for (String line : descriptionLines) {
                int lineWidth = 1 + line.length();
                maxLineLength = Math.max(maxLineLength, lineWidth);
            }
        }

        for (int i = 0; i < commonGoalCards.size(); i++) {
            System.out.print("╭" + "─".repeat(maxLineLength) + "╮");
        }
        System.out.println();

        for (CommonGoalCard card : commonGoalCards) {
            String commonCard = " Common Card: " + CLIColors.PURPLE_BRIGHT + card.getIndex() + CLIColors.RESET;
            String points = " Points:" + CLIColors.PURPLE_BRIGHT + " 8 " + CLIColors.RESET;
            int commonCardLength = commonCard.replaceAll("\\x1B\\[[;\\d]*m", "").length();
            int pointsLength = points.replaceAll("\\x1B\\[[;\\d]*m", "").length();
            int padding = Math.max(0, maxLineLength - (commonCardLength + pointsLength));
            System.out.print("│" + commonCard + " ".repeat(padding) + points + "│");
        }
        System.out.println();

        int maxLinesNumber = 2;
        for (int i = 0; i < maxLinesNumber; i++) {
            for (CommonGoalCard card : commonGoalCards) {
                String description = card.getDescription();
                String[] descriptionLines = description.split("\n");
                if (i < descriptionLines.length) {
                    String line = " " + descriptionLines[i];
                    int padding = Math.max(0, maxLineLength - line.length());
                    String newLineWithPadding = line + " ".repeat(padding);
                    System.out.print("│" + newLineWithPadding + "│");
                } else {
                    System.out.print("│" + " ".repeat(maxLineLength) + "│");
                }
            }
            System.out.println();
        }

        for (int i = 0; i < commonGoalCards.size(); i++) {
            System.out.print("╰" + "─".repeat(maxLineLength) + "╯");
        }
        System.out.println();
    }

    private void printBag() {
        System.out.println("Remaining item tiles in the bag: " + CLIColors.PURPLE_BRIGHT + virtualModel.getBag() + CLIColors.RESET);
    }

    protected void printLeaderBoard() {
        List<Player> players = virtualModel.getPlayers();
        players.sort(Comparator.comparingInt(Player::getTotPoints).reversed());
        String headerFormat = CLIColors.GREEN + "║" + CLIColors.CYAN + " %-4s " + CLIColors.GREEN + "║" + CLIColors.CYAN + " %-20s " + CLIColors.GREEN + "║" + CLIColors.CYAN + " %-5s " + CLIColors.GREEN + "║" + CLIColors.RESET;
        String[] colors = {CLIColors.YELLOW_BRIGHT, CLIColors.RED_BRIGHT, CLIColors.PURPLE_BRIGHT, CLIColors.BLUE_BRIGHT};

        System.out.println(CLIColors.GREEN + "╔══════╦══════════════════════╦════════╗" + CLIColors.RESET);
        System.out.printf(headerFormat, "Rank", "Leaderboard", "Points");
        System.out.println();
        System.out.println(CLIColors.GREEN + "╠══════╬══════════════════════╬════════╣" + CLIColors.RESET);

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.printf(CLIColors.GREEN + "║" + CLIColors.RESET + colors[i] + "   %s  " + CLIColors.RESET + CLIColors.GREEN + "║" + CLIColors.RESET + colors[i] + " %-20s " + CLIColors.RESET + CLIColors.GREEN + "║" + CLIColors.RESET + colors[i] + "    %s   " + CLIColors.RESET + CLIColors.GREEN + "║" + CLIColors.RESET, (i+1), player.getUsername(), player.getTotPoints());
            System.out.println();
            if (i < players.size() - 1) {
                System.out.println(CLIColors.GREEN + "╠══════╬══════════════════════╬════════╣" + CLIColors.RESET);
            } else {
                System.out.println(CLIColors.GREEN + "╚══════╩══════════════════════╩════════╝" + CLIColors.RESET);
            }
        }
    }

}
