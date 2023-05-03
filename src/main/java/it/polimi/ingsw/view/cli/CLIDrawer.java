package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.client.clientEntity.ClientBoardCell;

import it.polimi.ingsw.client.clientEntity.ClientCommonCard;
import it.polimi.ingsw.client.clientEntity.ClientLibrary;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;

import java.util.List;
import java.util.Objects;

import static it.polimi.ingsw.view.cli.CLIAssets.*;

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
        System.out.printf("%s%s%s%n", CLIConstants.WHITE_BRIGHT, CLIConstants.HORIZONTAL_LINE.repeat(300), CLIConstants.RESET);
    }

    /**
     * Print the board and the two libraries parallel to each other.
     */
    public void printGameObjects() {
        ClientBoardCell[][] board = virtualModel.getBoard();
        ClientLibrary currentLibrary = virtualModel.getMyLibrary();
        ClientLibrary personalCardLibrary = virtualModel.getPersonalGoalCard();

        System.out.println(PRINT_OBJECTS_TITLE);
        printFirstRow(board, currentLibrary.getItemTileTypesGrid(), personalCardLibrary.getItemTileTypesGrid());
        printfTopLine(board, currentLibrary.getItemTileTypesGrid(), personalCardLibrary.getItemTileTypesGrid());

        for (int row = 0; row < board.length; row++) {
            printBoardCells(board, row);
            printLibraries(currentLibrary.getItemTileTypesGrid(), personalCardLibrary.getItemTileTypesGrid(), row);
            printMiddleLine(board, currentLibrary.getItemTileTypesGrid(), personalCardLibrary.getItemTileTypesGrid(), row);
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
    private void printLibraries(ItemTileType[][] currentLibrary, ItemTileType[][] personalCardLibrary, int row) {
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
    private void printFirstRow(ClientBoardCell[][] board, ItemTileType[][] currentLibrary, ItemTileType[][] personalCardLibrary) {
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
    private void printfTopLine(ClientBoardCell[][] board, ItemTileType[][] currentLibrary, ItemTileType[][] personalCardLibrary) {
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
        sb.append(offset).append(CLIConstants.YELLOW_BOLD).append(CLIConstants.CORNER_TOP_LEFT).append(CLIConstants.RESET);

        for (int col = 0; col < numColumns - 1; col++) {
            sb.append(CLIConstants.YELLOW_BOLD).append(CLIConstants.HORIZONTAL_LINE.repeat(5)).append(CLIConstants.T_DOWN).append(CLIConstants.RESET);
        }
        sb.append(CLIConstants.YELLOW_BOLD).append(CLIConstants.HORIZONTAL_LINE.repeat(5)).append(CLIConstants.CORNER_TOP_RIGHT).append(CLIConstants.RESET);

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
    private void printMiddleLine(ClientBoardCell[][] board, ItemTileType[][] currentLibrary, ItemTileType[][] personalCardLibrary, int row) {
        if (row < currentLibrary.length - 1) {
            printMiddleLineDuplicate(board, currentLibrary, BOARD_AND_LIBRARY_MIDDLE_FRAME_FORMAT);
            System.out.print(BOARD_SHORT_DISTANCE_SEPARATOR_FRAME_FORMAT);
            for (int col = 0; col < personalCardLibrary[0].length; col++) {
                System.out.print(BOARD_AND_LIBRARY_MIDDLE_FRAME_FORMAT);
            }
            System.out.println(CLIConstants.YELLOW_BOLD + CLIConstants.T_LEFT + CLIConstants.RESET);
        } else if (row == currentLibrary.length - 1) {
            printMiddleLineDuplicate(board, currentLibrary, LIBRARY_BOTTOM_FRAME_FORMAT);
            System.out.print(CLIConstants.YELLOW_BOLD + CLIConstants.CORNER_BOTTOM_RIGHT + " ".repeat(6) + CLIConstants.RESET);
            for (int col = 0; col < personalCardLibrary[0].length; col++) {
                System.out.print(LIBRARY_BOTTOM_FRAME_FORMAT);
            }
            System.out.println(CLIConstants.YELLOW_BOLD + CLIConstants.CORNER_BOTTOM_RIGHT + CLIConstants.RESET);
        } else if (row < board.length - 1) {
            System.out.print("    ");
            for (int col = 0; col < board.length; col++) {
                System.out.print(BOARD_AND_LIBRARY_MIDDLE_FRAME_FORMAT);
            }
            System.out.println(CLIConstants.YELLOW_BOLD + CLIConstants.T_LEFT + CLIConstants.RESET);
        }
    }

    /**
     * Refactor to avoid duplicate code.
     *
     * @param board                            is the board to print
     * @param currentLibrary                   is the current library to print
     * @param boardAndLibraryMiddleFrameFormat is the format of the middle frame
     */
    private void printMiddleLineDuplicate(ClientBoardCell[][] board, ItemTileType[][] currentLibrary, String boardAndLibraryMiddleFrameFormat) {
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
    private void printBottomLine(ClientBoardCell[][] board) {
        System.out.print(" ".repeat(4) + CLIConstants.YELLOW_BOLD + CLIConstants.CORNER_BOTTOM_LEFT + CLIConstants.RESET);
        for (int col = 0; col < board.length - 1; col++) {
            System.out.print(CLIConstants.YELLOW_BOLD + CLIConstants.HORIZONTAL_LINE.repeat(5) + CLIConstants.T_UP + CLIConstants.RESET);
        }
        System.out.println(CLIConstants.YELLOW_BOLD + CLIConstants.HORIZONTAL_LINE.repeat(5) + CLIConstants.CORNER_BOTTOM_RIGHT + CLIConstants.RESET);
    }

    /**
     * Print the board cells.
     *
     * @param board is the board to print
     * @param row   is the current row
     */
    private void printBoardCells(ClientBoardCell[][] board, int row) {
        String rowLetter = CARDINALITY_MAP_ROW.get(row);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format(" %s  %s" + CLIConstants.VERTICAL_LINE + "%s", rowLetter, CLIConstants.YELLOW_BOLD, CLIConstants.RESET));
        for (int col = 0; col < board.length; col++) {
            if (board[row][col].isPlayable()) {
                stringBuilder.append(ITEM_TILES_TYPES_CLI_COLORS.get((board[row][col].getType())));
            } else {
                stringBuilder.append(CLIConstants.BLUE_BACKGROUND_BRIGHT + "     " + CLIConstants.RESET);
            }
            stringBuilder.append(CLIConstants.YELLOW_BOLD + CLIConstants.VERTICAL_LINE + CLIConstants.RESET);
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
    private void printLibraryCells(ItemTileType[][] library, int row, boolean isCurrentLibrary) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("  ").append(CARDINALITY_MAP_ROW.get(row)).append(" ");
        for (int col = 0; col < library[0].length; col++) {
            stringBuilder.append(CLIConstants.YELLOW_BOLD + CLIConstants.VERTICAL_LINE + CLIConstants.RESET).append(ITEM_TILES_TYPES_CLI_COLORS.get(library[row][col]));
        }
        stringBuilder.append(CLIConstants.YELLOW_BOLD + CLIConstants.VERTICAL_LINE + CLIConstants.RESET);

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
        List<ClientCommonCard> commonGoalCards = virtualModel.getCommonGoalCards();

        int maxLineLength = 0;
        for (ClientCommonCard card : commonGoalCards) {
            String description = card.getDescription();
            String[] descriptionLines = description.split("\n");
            for (String line : descriptionLines) {
                int lineWidth = 1 + line.length();
                maxLineLength = Math.max(maxLineLength, lineWidth);
            }
        }

        for (int i = 0; i < commonGoalCards.size(); i++) {
            System.out.printf(CLIConstants.SMOOTH_CORNER_TOP_LEFT + CLIConstants.HORIZONTAL_LINE.repeat(maxLineLength) + CLIConstants.SMOOTH_CORNER_TOP_RIGHT);
        }
        System.out.println();

        for (ClientCommonCard card : commonGoalCards) {
            String commonCard = " Common Card: " + CLIConstants.PURPLE_BRIGHT + card.getIndex() + CLIConstants.RESET;
            String points = " Points:" + CLIConstants.PURPLE_BRIGHT + card.getCurrentPoints() + CLIConstants.RESET;
            int commonCardLength = commonCard.replaceAll("\\x1B\\[[;\\d]*m", "").length();
            int pointsLength = points.replaceAll("\\x1B\\[[;\\d]*m", "").length();
            int padding = Math.max(0, maxLineLength - (commonCardLength + pointsLength));
            System.out.print(CLIConstants.VERTICAL_LINE + commonCard + " ".repeat(padding) + points + CLIConstants.VERTICAL_LINE);
        }
        System.out.println();

        int maxLinesNumber = 2;
        for (int i = 0; i < maxLinesNumber; i++) {
            for (ClientCommonCard card : commonGoalCards) {
                String description = card.getDescription();
                String[] descriptionLines = description.split("\n");
                if (i < descriptionLines.length) {
                    String line = " " + descriptionLines[i];
                    int padding = Math.max(0, maxLineLength - line.length());
                    String newLineWithPadding = line + " ".repeat(padding);
                    System.out.print(CLIConstants.VERTICAL_LINE + newLineWithPadding + CLIConstants.VERTICAL_LINE);
                } else {
                    System.out.print(CLIConstants.VERTICAL_LINE + " ".repeat(maxLineLength) + CLIConstants.VERTICAL_LINE);
                }
            }
            System.out.println();
        }

        for (int i = 0; i < commonGoalCards.size(); i++) {
            System.out.print(CLIConstants.SMOOTH_CORNER_BOTTOM_LEFT + CLIConstants.HORIZONTAL_LINE.repeat(maxLineLength) + CLIConstants.SMOOTH_CORNER_BOTTOM_RIGHT);
        }
        System.out.println();
    }

    /**
     * Prints the remaining item tiles in the bag.
     * Don't know if it's useful.
     */
    private void printBag() {
        System.out.println("Remaining item tiles in the bag: " + CLIConstants.PURPLE_BRIGHT + virtualModel.getBag() + CLIConstants.RESET);
    }

    /**
     * Prints the leader board.
     */
    protected void printLeaderBoard(boolean isWinner) {
        List<Pair<String, Integer>> leaderBoards = virtualModel.getLeaderBoard();

        String[] colors = {CLIConstants.YELLOW_BRIGHT, CLIConstants.RED_BRIGHT, CLIConstants.PURPLE_BRIGHT, CLIConstants.BLUE_BRIGHT};

        System.out.println(LEADERBOARD_TOP_FRAME_FORMAT);
        System.out.printf(LEADERBOARD_HEADER_FORMAT, "Rank", "Leaderboard", "Points");
        System.out.println();
        System.out.println(LEADERBOARD_MIDDLE_FRAME_FORMAT);

        for (int i = 0; i < leaderBoards.size(); i++) {
            System.out.printf(CLIConstants.GREEN + CLIConstants.VERTICAL_LINE_DOUBLE + CLIConstants.RESET + colors[i] + "   %s  " + CLIConstants.RESET + CLIConstants.GREEN + CLIConstants.VERTICAL_LINE_DOUBLE + CLIConstants.RESET + colors[i] + " %-20s " + CLIConstants.RESET + CLIConstants.GREEN + CLIConstants.VERTICAL_LINE_DOUBLE + CLIConstants.RESET + colors[i] + "    %s   " + CLIConstants.RESET + CLIConstants.GREEN + CLIConstants.VERTICAL_LINE_DOUBLE + CLIConstants.RESET, (i + 1), leaderBoards.get(i).getValue0(), leaderBoards.get(i).getValue1());
            System.out.println();
            if (i < leaderBoards.size() - 1) {
                System.out.println(LEADERBOARD_MIDDLE_FRAME_FORMAT);
            } else {
                System.out.println(LEADERBOARD_BOTTOM_FRAME_FORMAT);

            }
        }
        if (isWinner) {
            System.out.println(CLIAssets.output + "Congratulations, you won!");
        } else {
            System.out.println(CLIAssets.output + "You lost, better luck next time!");
        }
    }
}
