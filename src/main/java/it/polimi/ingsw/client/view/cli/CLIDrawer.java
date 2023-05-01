package it.polimi.ingsw.client.view.cli;

import it.polimi.ingsw.client.view.VirtualModel;
import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;

import java.util.Comparator;
import java.util.List;

import static it.polimi.ingsw.client.view.cli.CLIAssets.*;
import static it.polimi.ingsw.client.view.cli.CLIConstants.*;

/**
 * This class is used to print the game objects on the CLI.
 */
public class CLIDrawer {
    /**
     * It is the virtual model of the game.
     */
    private final VirtualModel virtualModel;

    /**
     * It is the constructor of the class.
     *
     * @param virtualModel is the virtual model of the game
     */
    public CLIDrawer(VirtualModel virtualModel) {
        this.virtualModel = virtualModel;
    }

    /**
     * Prints everything related to the turn of the player.
     */
    public void printGame() {
        printGameObjects();
        printCommonGoalCards();
        printSeparator();
    }

    /**
     * Prints a basic separator.
     */
    protected void printSeparator() {
        System.out.printf("%s%s%s%n", WHITE_BRIGHT, HORIZONTAL_LINE.repeat(300), RESET);
    }

    /**
     * Print the board and the two libraries parallel to each other.
     */
    public void printGameObjects() {
        BoardCell[][] board = virtualModel.getBoard();
        ItemTile[][] currentLibrary = virtualModel.getLibrary();
        ItemTile[][] personalCardLibrary = virtualModel.getPersonalGoalCard();

        System.out.println(PRINT_OBJECTS_TITLE);
        printFirstRow(board, currentLibrary, personalCardLibrary);
        printfTopLine(board, currentLibrary, personalCardLibrary);

        for (int row = 0; row < board.length; row++) {
            printBoardCells(board, row);
            printLibraries(currentLibrary, personalCardLibrary, row);
            printMiddleLine(board, currentLibrary, personalCardLibrary, row);
        }
        printBottomLine(board);
    }

    /**
     * Print the libraries.
     *
     * @param currentLibrary      is the current library to print
     * @param personalCardLibrary is the library based on the personal goal card to print
     * @param row                 is the row of the board
     */
    private void printLibraries(ItemTile[][] currentLibrary, ItemTile[][] personalCardLibrary, int row) {
        if (row < currentLibrary.length && row < personalCardLibrary.length) {
            System.out.print("     ");
            printLibraryCells(currentLibrary, row, true);
            System.out.print("  ");
            printLibraryCells(personalCardLibrary, row, false);
        } else {
            System.out.println();
        }
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

        sb.append(" ".repeat(5));
        for (int col = 0; col < board.length; col++) {
            sb.append(String.format("  %s   ", CARDINALITY_MAP_COLUMN.get(col)));
        }
        sb.append("          ");
        for (int col = 0; col < currentLibrary[0].length; col++) {
            sb.append(String.format("  %s   ", CARDINALITY_MAP_COLUMN.get(col)));
        }
        sb.append("       ");
        for (int col = 0; col < personalCardLibrary[0].length; col++) {
            sb.append(String.format("  %s   ", CARDINALITY_MAP_COLUMN.get(col)));
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
        printDelimiterLine(board.length, " ".repeat(4));
        printDelimiterLine(currentLibrary[0].length, " ".repeat(9));
        printDelimiterLine(personalCardLibrary[0].length, " ".repeat(6));
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
        sb.append(offset).append(YELLOW_BOLD).append(CORNER_TOP_LEFT).append(RESET);

        for (int col = 0; col < numColumns - 1; col++) {
            sb.append(YELLOW_BOLD).append(HORIZONTAL_LINE.repeat(5)).append(T_DOWN).append(RESET);
        }
        sb.append(YELLOW_BOLD).append(HORIZONTAL_LINE.repeat(5)).append(CORNER_TOP_RIGHT).append(RESET);

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
            printMiddleLineDuplicate(board, currentLibrary, BOARD_AND_LIBRARY_MIDDLE_FRAME_FORMAT);
            System.out.print(BOARD_SHORT_DISTANCE_SEPARATOR_FRAME_FORMAT);
            for (int col = 0; col < personalCardLibrary[0].length; col++) {
                System.out.print(BOARD_AND_LIBRARY_MIDDLE_FRAME_FORMAT);
            }
            System.out.println(YELLOW_BOLD + T_LEFT + RESET);
        } else if (row == currentLibrary.length - 1) {
            printMiddleLineDuplicate(board, currentLibrary, LIBRARY_BOTTOM_FRAME_FORMAT);
            System.out.print(YELLOW_BOLD + CORNER_BOTTOM_RIGHT + " ".repeat(6) + RESET);
            for (int col = 0; col < personalCardLibrary[0].length; col++) {
                System.out.print(LIBRARY_BOTTOM_FRAME_FORMAT);
            }
            System.out.println(YELLOW_BOLD + CORNER_BOTTOM_RIGHT + RESET);
        } else if (row < board.length - 1) {
            System.out.print("    ");
            for (int col = 0; col < board.length; col++) {
                System.out.print(BOARD_AND_LIBRARY_MIDDLE_FRAME_FORMAT);
            }
            System.out.println(YELLOW_BOLD + T_LEFT + RESET);
        }
    }

    /**
     * Refactor to avoid duplicate code.
     *
     * @param board                            is the board to print
     * @param currentLibrary                   is the current library to print
     * @param boardAndLibraryMiddleFrameFormat is the format of the middle frame
     */
    private void printMiddleLineDuplicate(BoardCell[][] board, ItemTile[][] currentLibrary, String boardAndLibraryMiddleFrameFormat) {
        System.out.print("    ");
        for (int col = 0; col < board.length; col++) {
            System.out.print(BOARD_AND_LIBRARY_MIDDLE_FRAME_FORMAT);
        }
        System.out.print(BOARD_LONG_DISTANCE_SEPARATOR_FRAME_FORMAT);
        for (int col = 0; col < currentLibrary[0].length; col++) {
            System.out.print(boardAndLibraryMiddleFrameFormat);
        }
    }

    /**
     * Print the bottom line of the board.
     *
     * @param board is the board to print
     */
    private void printBottomLine(BoardCell[][] board) {
        System.out.print(YELLOW_BOLD + CORNER_BOTTOM_LEFT + RESET);
        for (int col = 0; col < board.length - 1; col++) {
            System.out.print(YELLOW_BOLD + HORIZONTAL_LINE.repeat(5) + T_UP + RESET);
        }
        System.out.println(YELLOW_BOLD + HORIZONTAL_LINE.repeat(5) + CORNER_BOTTOM_RIGHT + RESET);
    }

    /**
     * Print the board cells.
     *
     * @param board is the board to print
     * @param row   is the current row
     */
    private void printBoardCells(BoardCell[][] board, int row) {
        String rowLetter = CARDINALITY_MAP_ROW.get(row);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format(" %s  %s" + VERTICAL_LINE + "%s", rowLetter, YELLOW_BOLD, RESET));
        for (int col = 0; col < board.length; col++) {
            if (board[row][col].isPlayable()) {
                stringBuilder.append(ITEM_TILES_TYPES_CLI_COLORS.get((board[row][col].getItemTile().getItemTileType())));
            } else {
                stringBuilder.append(BLUE_BACKGROUND_BRIGHT + "     " + RESET);
            }
            stringBuilder.append(YELLOW_BOLD + VERTICAL_LINE + RESET);
        }
        System.out.print(stringBuilder);
    }

    /**
     * Print the current library cells.
     *
     * @param library          is the library to print
     * @param row              is the current row
     * @param isCurrentLibrary is true if the library is the current library, false otherwise
     */
    private void printLibraryCells(ItemTile[][] library, int row, boolean isCurrentLibrary) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("  ").append(CARDINALITY_MAP_ROW.get(row)).append(" ");
        for (int col = 0; col < library[0].length; col++) {
            stringBuilder.append(YELLOW_BOLD + VERTICAL_LINE + RESET).append(ITEM_TILES_TYPES_CLI_COLORS.get(library[row][col].getItemTileType()));
        }
        stringBuilder.append(YELLOW_BOLD + VERTICAL_LINE + RESET);

        if (isCurrentLibrary) {
            System.out.print(stringBuilder);
        } else {
            System.out.println(stringBuilder);
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
            System.out.printf(SMOOTH_CORNER_TOP_LEFT + HORIZONTAL_LINE.repeat(maxLineLength) + SMOOTH_CORNER_TOP_RIGHT);
        }
        System.out.println();

        for (CommonGoalCard card : commonGoalCards) {
            String commonCard = " Common Card: " + PURPLE_BRIGHT + card.getIndex() + RESET;
            String points = " Points:" + PURPLE_BRIGHT + " 8 " + RESET;
            int commonCardLength = commonCard.replaceAll("\\x1B\\[[;\\d]*m", "").length();
            int pointsLength = points.replaceAll("\\x1B\\[[;\\d]*m", "").length();
            int padding = Math.max(0, maxLineLength - (commonCardLength + pointsLength));
            System.out.print(VERTICAL_LINE + commonCard + " ".repeat(padding) + points + VERTICAL_LINE);
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
                    System.out.print(VERTICAL_LINE + newLineWithPadding + VERTICAL_LINE);
                } else {
                    System.out.print(VERTICAL_LINE + " ".repeat(maxLineLength) + VERTICAL_LINE);
                }
            }
            System.out.println();
        }

        for (int i = 0; i < commonGoalCards.size(); i++) {
            System.out.print(SMOOTH_CORNER_BOTTOM_LEFT + HORIZONTAL_LINE.repeat(maxLineLength) + SMOOTH_CORNER_BOTTOM_RIGHT);
        }
        System.out.println();
    }

    /**
     * Prints the remaining item tiles in the bag.
     * Don't know if it's useful.
     */
    private void printBag() {
        System.out.println("Remaining item tiles in the bag: " + PURPLE_BRIGHT + virtualModel.getBag() + RESET);
    }

    /**
     * Prints the leader board.
     */
    protected void printLeaderBoard() {
        List<Player> players = virtualModel.getPlayers();
        players.sort(Comparator.comparingInt(Player::getTotPoints).reversed());
        String[] colors = {YELLOW_BRIGHT, RED_BRIGHT, PURPLE_BRIGHT, BLUE_BRIGHT};

        System.out.println(LEADERBOARD_TOP_FRAME_FORMAT);
        System.out.printf(LEADERBOARD_HEADER_FORMAT, "Rank", "Leaderboard", "Points");
        System.out.println();
        System.out.println(LEADERBOARD_MIDDLE_FRAME_FORMAT);

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.printf(GREEN + VERTICAL_LINE_DOUBLE + RESET + colors[i] + "   %s  " + RESET + GREEN + VERTICAL_LINE_DOUBLE + RESET + colors[i] + " %-20s " + RESET + GREEN + VERTICAL_LINE_DOUBLE + RESET + colors[i] + "    %s   " + RESET + GREEN + VERTICAL_LINE_DOUBLE + RESET, (i + 1), player.getUsername(), player.getTotPoints());
            System.out.println();
            if (i < players.size() - 1) {
                System.out.println(LEADERBOARD_MIDDLE_FRAME_FORMAT);
            } else {
                System.out.println(LEADERBOARD_BOTTOM_FRAME_FORMAT);

            }
        }
    }
}
