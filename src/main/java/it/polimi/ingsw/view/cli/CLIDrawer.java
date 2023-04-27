package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.gameEntity.Bag;
import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameEntity.personal_cards.Goal;
import it.polimi.ingsw.model.gameState.GameData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CLIDrawer {
    private final GameData gameData;

    public CLIDrawer(GameData gameData) {
        this.gameData = gameData;
    }

    public void printGame() {
        printGameObjects();
        printCards();
        printSeparator();
    }

    protected void printSeparator() {
        System.out.printf("%s%s%s%n", CLIColors.WHITE_BRIGHT, "-".repeat(300), CLIColors.RESET);
    }

    /**
     * Print the board and the two libraries parallel to each other.
     */
    public void printGameObjects() {
        Board board = gameData.getBoard();
        Library currentLibrary = gameData.getCurrentPlayer().getLibrary();
        Library personalCardLibrary = new Library();
        for (Goal goal : gameData.getCurrentPlayer().getPersonalGoalCard().getGoals()) {
            personalCardLibrary.setItemTile(goal.getRow(), goal.getColumn(), new ItemTile(goal.getItemTileType()));
        }

        System.out.println(CLIAssets.PRINT_OBJECTS_TITLE);
        printFirstRow(board, currentLibrary, personalCardLibrary);
        printfTopLine(board, currentLibrary, personalCardLibrary);

        for (int row = 0; row < board.getROWS(); row++) {
            printBoardCells(board, row);
            if (row < currentLibrary.getROWS() && row < personalCardLibrary.getROWS()) {
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
    private void printFirstRow(Board board, Library currentLibrary, Library personalCardLibrary) {
        StringBuilder sb = new StringBuilder();

        sb.append("     ");
        for (int col = 0; col < board.getCOLUMNS(); col++) {
            sb.append(String.format("  %s   ", CLIAssets.CARDINALITY_MAP_COLUMN.get(col)));
        }
        sb.append("          ");
        for (int col = 0; col < currentLibrary.getCOLUMNS(); col++) {
            sb.append(String.format("  %s   ", CLIAssets.CARDINALITY_MAP_COLUMN.get(col)));
        }
        sb.append("       ");
        for (int col = 0; col < personalCardLibrary.getCOLUMNS(); col++) {
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
    private void printfTopLine(Board board, Library currentLibrary, Library personalCardLibrary) {
        printDelimiterLine(board.getCOLUMNS(), "    ");
        printDelimiterLine(currentLibrary.getCOLUMNS(), "         ");
        printDelimiterLine(personalCardLibrary.getCOLUMNS(), "      ");
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
    private void printMiddleLine(Board board, Library currentLibrary, Library personalCardLibrary, int row) {
        if (row < currentLibrary.getROWS() - 1) {
            System.out.print("    ");
            for (int col = 0; col < board.getCOLUMNS(); col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "├─────" + CLIColors.RESET);
            }
            System.out.print(CLIColors.YELLOW_BOLD + "┤         " + CLIColors.RESET);
            for (int col = 0; col < currentLibrary.getCOLUMNS(); col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "├─────" + CLIColors.RESET);
            }
            System.out.print(CLIColors.YELLOW_BOLD + "┤      " + CLIColors.RESET);
            for (int col = 0; col < personalCardLibrary.getCOLUMNS(); col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "├─────" + CLIColors.RESET);
            }
            System.out.println(CLIColors.YELLOW_BOLD + "┤" + CLIColors.RESET);
        } else if (row == currentLibrary.getROWS() - 1) {
            System.out.print("    ");
            for (int col = 0; col < board.getCOLUMNS(); col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "├─────" + CLIColors.RESET);
            }
            System.out.print(CLIColors.YELLOW_BOLD + "┤         " + CLIColors.RESET);
            for (int col = 0; col < currentLibrary.getCOLUMNS(); col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "└─────" + CLIColors.RESET);
            }
            System.out.print(CLIColors.YELLOW_BOLD + "┘      " + CLIColors.RESET);
            for (int col = 0; col < personalCardLibrary.getCOLUMNS(); col++) {
                System.out.print(CLIColors.YELLOW_BOLD + "└─────" + CLIColors.RESET);
            }
            System.out.println(CLIColors.YELLOW_BOLD + "┘" + CLIColors.RESET);
        } else if (row < board.getROWS() - 1) {
            System.out.print("    ");
            for (int col = 0; col < board.getCOLUMNS(); col++) {
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
    private void printBottomLine(Board board) {
        System.out.print(CLIColors.YELLOW_BOLD + "    └" + CLIColors.RESET);
        for (int col = 0; col < board.getCOLUMNS() - 1; col++) {
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
    private void printBoardCells(Board board, int row) {
        String rowLetter = CLIAssets.CARDINALITY_MAP_ROW.get(row);
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(" %s  %s│%s", rowLetter, CLIColors.YELLOW_BOLD, CLIColors.RESET));
        for (int col = 0; col < board.getCOLUMNS(); col++) {
            if (board.getBoardCell(row, col).isPlayable()) {
                sb.append(CLIAssets.ITEM_TILES_TYPES_CLI_COLORS.get((board.getBoardCell(row, col).getItemTile().getItemTileType())));
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
    private void printLibraryCells(Library library, int row, boolean isCurrentLibrary) {
        StringBuilder sb = new StringBuilder();

        sb.append("  ").append(CLIAssets.CARDINALITY_MAP_ROW.get(row)).append(" ");
        for (int col = 0; col < library.getCOLUMNS(); col++) {
            sb.append(CLIColors.YELLOW_BOLD + "│" + CLIColors.RESET).append(CLIAssets.ITEM_TILES_TYPES_CLI_COLORS.get(library.getItemTile(row, col).getItemTileType()));
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
    public void printCommonGoalCards() {
        List<CommonGoalCard> commonGoalCards = gameData.getCommonGoalCardsList();
        List<List<String>> cardDescriptions = new ArrayList<>();
        int maxLineWidth = 0;
        for (CommonGoalCard commonGoalCard : commonGoalCards) {
            String description = commonGoalCard.getDescription();
            String[] lines = description.split("\n");
            cardDescriptions.add(Arrays.asList(lines));
            for (String line : lines) {
                maxLineWidth = Math.max(maxLineWidth, line.length());
            }
        }

        int maxRows = cardDescriptions.stream().mapToInt(List::size).max().orElse(0);

        // Stampa la riga superiore della cornice
        for (int i = 0; i < cardDescriptions.size(); i++) {
            System.out.print("┌" + "─".repeat(maxLineWidth) + "┐");
            if (i < cardDescriptions.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        for (int i = 0; i < cardDescriptions.size(); i++) {
            System.out.printf("│CommonGoalCard %d Points:8%-" + (maxLineWidth - ("CommonGoalCard ").length() - 4 - " Points:8".length() - (i * 2)) + "s│", commonGoalCards.get(i).getIndex(), "");
            if (i < cardDescriptions.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        for (int row = 0; row < maxRows; row++) {
            for (int i = 0; i < cardDescriptions.size(); i++) {
                List<String> descriptionLines = cardDescriptions.get(i);

                if (row < descriptionLines.size()) {
                    System.out.printf("│%-" + maxLineWidth + "s│", descriptionLines.get(row));
                } else {
                    System.out.printf("│%-" + maxLineWidth + "s│", "");
                }

                if (i < cardDescriptions.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        // Stampa la riga inferiore della cornice
        for (int i = 0; i < cardDescriptions.size(); i++) {
            System.out.print("└" + "─".repeat(maxLineWidth) + "┘");
            if (i < cardDescriptions.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private void printCards(){
        int maxLineWidth = 66;
        List<CommonGoalCard> commonGoalCards = gameData.getCommonGoalCardsList();
        String commonCard = " Common Card: " + commonGoalCards.get(0).getIndex();
        String points = " Points: x ";
        int commonCardLength = commonCard.length();
        int pointsLength = points.length();
        for(int i = 0; i < commonGoalCards.size(); i++){
            System.out.print("┌" + "─".repeat(maxLineWidth) + "┐");
        }
        System.out.println();
        System.out.println("|" + " Common Card: " + commonGoalCards.get(0).getIndex() + " ".repeat(maxLineWidth-(commonCardLength + pointsLength)) + "Points: 8 " + "||" + " Common Card: " + commonGoalCards.get(1).getIndex() +" ".repeat(maxLineWidth-25) + "Points: 8 " + "|");
        for(int i = 0; i < 2; i++){
            System.out.println("|" + " ".repeat(maxLineWidth) + "||" + " ".repeat(maxLineWidth) + "|");
        }
        for(int i = 0; i < commonGoalCards.size(); i++){
            System.out.print("└" + "─".repeat(maxLineWidth) + "┘");
        }
        System.out.println();
    }

    public void printItemTilesBag() {
        Bag bag = gameData.getBag();
        System.out.println("Remaining item tiles: " + bag.getItemTiles().size());
    }
}
