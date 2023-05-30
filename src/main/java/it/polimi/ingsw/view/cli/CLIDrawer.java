package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.client.VirtualModel;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;

import static it.polimi.ingsw.view.cli.CLIConstants.*;

/**
 * This class is used to print the game objects on the CLI.
 */
public class CLIDrawer {

    /**
     * It is the virtual model of the game.
     */
    private final VirtualModel virtualModel;
    /**
     * It is the parser of the CLI.
     */
    private final CLIParser parser;

    /**
     * Default constructor, initializes the parser and the virtual model.
     *
     * @param virtualModel is the virtual model of the game
     */
    public CLIDrawer(VirtualModel virtualModel) {
        this.virtualModel = virtualModel;
        this.parser = new CLIParser();
    }

    /**
     * Prints everything related to the turn of the player.
     */
    public void printGame() {
        System.out.println(MYSHELFIE_TITLE);
        System.out.println();
        List<String> extraInfosStringList = new ArrayList<>();
        extraInfosStringList.add(ITEM_TILES_TYPE_COLOR_LEGEND);
        for (String username : virtualModel.getClientUsernameLibrary().keySet()) {
            if (!username.equals(virtualModel.getMyUsername())) {
                extraInfosStringList.add(getOtherPlayerLibraryAsString(username));
            }
        }
        extraInfosStringList.add(getGameInfoAsString());

        System.out.println(concatenateStringsHorizontally(extraInfosStringList));

        String boardString = getGridAsString(virtualModel.getBoard(), "Board", 0);
        String libraryString = getGridAsString(virtualModel.getLibrary(), "Library", 1);
        String personalGoalCardString = getGridAsString(virtualModel.getPersonalGoalCard(), "Personal Card", 2);

        String libraryAndPersonalGoalCardString = concatenateStringsHorizontally(Arrays.asList(libraryString, personalGoalCardString));
        String libraryPersonalGoalAndCommonCardsString = concatenateStringsVertically(Arrays.asList(libraryAndPersonalGoalCardString, getCommonCardsAsString(virtualModel.getCommonGoalCards())));

        System.out.println(concatenateStringsHorizontally(Arrays.asList(boardString, libraryPersonalGoalAndCommonCardsString)));
        printSeparator();
    }

    /**
     * This method is used to concatenate a list of strings horizontally.
     *
     * @param strings is the list of objects as string to print
     */
    private String concatenateStringsHorizontally(List<String> strings) {
        StringBuilder result = new StringBuilder();
        List<String[]> splitStrings = new ArrayList<>();
        int maxLines = 0;

        for (String string : strings) {
            String[] lines = string.split("\n");
            splitStrings.add(lines);
            maxLines = Math.max(maxLines, lines.length);
        }

        for (int i = 0; i < maxLines; i++) {
            for (String[] splitString : splitStrings) {
                if (i < splitString.length) {
                    result.append(splitString[i]);
                } else {
                    result.append(" ".repeat(splitString[0].length()));
                }
                result.append("   ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    /**
     * This method is used to concatenate a list of strings vertically.
     *
     * @param strings is the list of strings to concatenate
     * @return the concatenated string
     */
    private String concatenateStringsVertically(List<String> strings) {
        StringBuilder result = new StringBuilder();
        for (String string : strings) {
            result.append(string).append("\n");
        }
        return result.toString();
    }

    /**
     * Prints a basic separator.
     */
    protected void printSeparator() {
        System.out.printf("%s%s%s%n", WHITE_BRIGHT, HORIZONTAL_LINE.repeat(150), RESET);
    }

    /**
     * This method converts a library grid into a string.
     * It is a small representation of the other players' libraries.
     *
     * @param username is the username of the player
     * @return the string representation of the library
     */
    private String getOtherPlayerLibraryAsString(String username) {
        ItemTileType[][] libraryGrid = virtualModel.getClientUsernameLibrary().get(username);
        int gridWidth = 3 * libraryGrid[0].length + 2;

        StringBuilder smallLibraryAsString = getTitlePadding(username, gridWidth);

        smallLibraryAsString.append(CORNER_TOP_LEFT).append(HORIZONTAL_LINE.repeat(3 * libraryGrid[0].length)).append(CORNER_TOP_RIGHT).append("\n");

        for (ItemTileType[] itemTileTypes : libraryGrid) {
            smallLibraryAsString.append(VERTICAL_LINE);
            for (int column = 0; column < libraryGrid[0].length; column++) {
                ItemTileType itemTileType = itemTileTypes[column];
                String itemTileTypeAsString = ITEM_TILES_TYPES_CLI_COLORS_SMALL.get(itemTileType);
                smallLibraryAsString.append(itemTileTypeAsString);
            }
            smallLibraryAsString.append(VERTICAL_LINE).append("\n");
        }
        smallLibraryAsString.append(CORNER_BOTTOM_LEFT).append(HORIZONTAL_LINE.repeat(3 * libraryGrid[0].length)).append(CORNER_BOTTOM_RIGHT).append("\n");
        return smallLibraryAsString.toString();
    }

    /**
     * This method converts a board grid into a string.
     *
     * @param grid     is the grid to convert
     * @param title    is the title of the grid
     * @param position is the position of the grid (not suer to keep it)
     * @return the string representation of the grid
     */
    public String getGridAsString(ItemTileType[][] grid, String title, int position) {
        int gridWidth = grid[0].length * 6 + 3;

        StringBuilder gridAsString = new StringBuilder();
        if (position == 0) {
            gridAsString.append("  ").append(getTitlePadding(title, gridWidth));
        } else {
            gridAsString.append(" ").append(getTitlePadding(title, gridWidth));
        }

        gridAsString.append("    ");
        for (int column = 0; column < grid[0].length; column++) {
            gridAsString.append(String.format("  %d   ", parser.getColumnValue(column)));
        }
        gridAsString.append("\n");

        gridStringHelper(grid, gridAsString, CORNER_TOP_LEFT, T_DOWN, CORNER_TOP_RIGHT);

        for (int row = 0; row < grid.length; row++) {
            gridAsString.append(String.format(" %s " + VERTICAL_LINE, parser.getRowValue(row)));
            for (int column = 0; column < grid[row].length; column++) {
                ItemTileType itemTileType = grid[row][column];
                String itemTileTypeAsString = ITEM_TILES_TYPES_CLI_COLORS.get(itemTileType);
                gridAsString.append(itemTileTypeAsString).append(VERTICAL_LINE);
            }
            gridAsString.append("\n");

            if (row < grid.length - 1) {
                gridStringHelper(grid, gridAsString, T_RIGHT, CROSS, T_LEFT);
            }
        }
        gridStringHelper(grid, gridAsString, CORNER_BOTTOM_LEFT, T_UP, CORNER_BOTTOM_RIGHT);
        return gridAsString.toString();
    }

    /**
     * This method helps the print of the grid.
     * It is used to avoid code repetition.
     *
     * @param grid         the grid to print
     * @param gridAsString the string to print
     * @param leftFrame    the left frame
     * @param middleFrame  the middle frame
     * @param rightFrame   the right frame
     */
    private void gridStringHelper(ItemTileType[][] grid, StringBuilder gridAsString, String leftFrame, String middleFrame, String rightFrame) {
        gridAsString.append("   ").append(leftFrame);
        for (int column = 0; column < grid[0].length; column++) {
            if (column > 0) {
                gridAsString.append(middleFrame);
            }
            gridAsString.append(HORIZONTAL_LINE.repeat(5));
        }
        gridAsString.append(rightFrame).append("\n");
    }

    /**
     * This method returns a string builder with the title centered.
     *
     * @param title is the title to center
     * @param width is the width of the frame
     * @return the string builder with the title centered
     */
    private StringBuilder getTitlePadding(String title, int width) {
        StringBuilder titlePadding = new StringBuilder();
        int titleLength = title.length();
        int paddingBefore = (width - titleLength) / 2;
        int paddingAfter = width - (paddingBefore + titleLength);
        titlePadding.append(" ".repeat(paddingBefore)).append(CYAN_BRIGHT).append(title).append(RESET).append(" ".repeat(paddingAfter)).append("\n");
        return titlePadding;
    }

    /**
     * This method returns the string representation of the common cards.
     *
     * @param cards is the list of common cards
     * @return the string representation of the common cards
     */
    private String getCommonCardsAsString(List<Triplet<Integer, Integer, String>> cards) {
        StringBuilder commonCardsString = new StringBuilder();
        commonCardsString.append("Common Cards:\n");

        for (Triplet<Integer, Integer, String> card : cards) {
            String cardIndex = YELLOW + card.getValue0() + CLIConstants.RESET + ")";
            String cardToken = "Token: " + RED_BRIGHT + card.getValue1() + CLIConstants.RESET;
            String cardDescription = card.getValue2();

            commonCardsString.append(cardIndex)
                    .append(card.getValue0() > 9 ? " " : "  ")
                    .append(cardToken)
                    .append(String.format(" %s ", VERTICAL_LINE))
                    .append(cardDescription)
                    .append("\n");
        }
        return commonCardsString.toString();
    }

    /**
     * This method returns the string representation of the game info.
     *
     * @return the string representation of the game info
     */
    private String getGameInfoAsString() {
        StringBuilder gameInfoAsString = new StringBuilder();
        String[] gameInfoLines = {
                " " + WHITE_UNDERLINED + "Game info:" + RESET,
                " Username: " + CYAN_BRIGHT + virtualModel.getMyUsername() + RESET,
                " Current player: " + PURPLE_BRIGHT + virtualModel.getCurrentPlayerUsername() + RESET,
                " Points: " + RED_BRIGHT + virtualModel.getMyPoints() + RESET,
                " Turn number: " + PURPLE_BRIGHT + parser.getColumnValue(virtualModel.getCurrentPlayerIndex()) + RESET + "/" + CYAN_BRIGHT + virtualModel.getClientUsernameLibrary().size() + RESET,
                " Chair: " + ((virtualModel.getMyUsername().equals(virtualModel.getPlayerUsername(0))) ? GREEN_BRIGHT + "true" + RESET : RED_BRIGHT + "false" + RESET),
                " Last message: " + GREEN_BRIGHT + virtualModel.getServerMessage() + RESET
        };
        for (String line : gameInfoLines) {
            gameInfoAsString.append(line);
            gameInfoAsString.append("\n");
        }
        return gameInfoAsString.toString();
    }

    /**
     * This method returns the string representation of the leader board.
     *
     * @param isWinner is true if the player is the winner
     * @return the string representation of the leader board
     */
    protected String getLeaderboardAsString(boolean isWinner) {
        List<Pair<String, Integer>> leaderboard = virtualModel.getLeaderBoard();

        String[] colors = {YELLOW_BRIGHT, RED_BRIGHT, PURPLE_BRIGHT, BLUE_BRIGHT};

        int maxNameLength = leaderboard.stream()
                .map(pair -> pair.getValue0().length())
                .max(Integer::compare)
                .orElse(0);

        StringBuilder leaderboardAsString = new StringBuilder();
        String format = "%s%-4s  %-" + maxNameLength + "s  %7s%s%n";
        leaderboardAsString.append(String.format(format, RESET, "Rank", "Leaderboard", "Points", ""));

        for (int i = 0; i < leaderboard.size(); i++) {
            String rank = String.valueOf(i + 1);
            String playerName = leaderboard.get(i).getValue0();
            String playerPoints = String.valueOf(leaderboard.get(i).getValue1());
            String playerColor = colors[i % colors.length];

            leaderboardAsString.append(String.format(format, playerColor, rank, playerName, playerPoints, RESET));
        }
        if (isWinner) {
            leaderboardAsString.append(MYSHELFIE_WINNER);
        } else {
            leaderboardAsString.append(MYSHELFIE_LOOSER);
        }
        return leaderboardAsString.toString();
    }
}