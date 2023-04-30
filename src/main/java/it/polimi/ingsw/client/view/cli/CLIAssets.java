package it.polimi.ingsw.client.view.cli;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.Map;

/**
 * Useful class to store all the assets used in the CLI.
 */
public class CLIAssets {

    /**
     * It is the title of the game.
     */
    public static final String MYSHELFIE_TITLE = ("""
                        
            ███╗   ███╗██╗   ██╗    ███████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗
            ████╗ ████║╚██╗ ██╔╝    ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝
            ██╔████╔██║ ╚████╔╝     ███████╗███████║█████╗  ██║     █████╗  ██║█████╗\s
            ██║╚██╔╝██║  ╚██╔╝      ╚════██║██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝ \s
            ██║ ╚═╝ ██║   ██║       ███████║██║  ██║███████╗███████╗██║     ██║███████╗
            ╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝
                                                                                       """)
            .replaceAll("█", CLIConstants.YELLOW_BRIGHT + "█" + CLIConstants.RESET)
            .replaceAll("([╔╗║╝═╚])", CLIConstants.RED_BRIGHT + "$1" + CLIConstants.RESET);
    /**
     * It is the regex used to check the validity of the username.
     */
    public static final String USERNAME_REGEX = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    /**
     * It is the regex used to check the validity of the coordinates.
     */
    public static final String COORDINATES_REGEX = "([A-I][1-9](-[A-I][1-9]){0,2})";
    /**
     * It is a map used to convert the cardinality of the board from integer to string.
     */
    public static final Map<Integer, String> CARDINALITY_MAP_ROW = Map.of(
            0, "A",
            1, "B",
            2, "C",
            3, "D",
            4, "E",
            5, "F",
            6, "G",
            7, "H",
            8, "I"
    );
    /**
     * It is a map used to convert the cardinality of the board from integer to integer+1.
     */
    public static final Map<Integer, Integer> CARDINALITY_MAP_COLUMN = Map.of(
            0, 1,
            1, 2,
            2, 3,
            3, 4,
            4, 5,
            5, 6,
            6, 7,
            7, 8,
            8, 9
    );
    /**
     * It is the title of the print objects method in the drawer.
     */
    public static final String PRINT_OBJECTS_TITLE = "                             Board                                              Library                          Personal Card";
    /**
     * It is a map that contains the association between the item tile type and the color used to print it.
     */
    protected static final Map<ItemTileType, String> ITEM_TILES_TYPES_CLI_COLORS = Map.of(
            ItemTileType.CAT, CLIConstants.GREEN_BRIGHT + " CAT " + CLIConstants.RESET,
            ItemTileType.BOOK, CLIConstants.WHITE_BRIGHT + " BOO " + CLIConstants.RESET,
            ItemTileType.GAME, CLIConstants.YELLOW_BRIGHT + " GAM " + CLIConstants.RESET,
            ItemTileType.FRAME, CLIConstants.BLUE_BRIGHT + " FRA " + CLIConstants.RESET,
            ItemTileType.TROPHY, CLIConstants.CYAN_BRIGHT + " TRO " + CLIConstants.RESET,
            ItemTileType.PLANT, CLIConstants.PURPLE_BRIGHT + " PLA " + CLIConstants.RESET,
            ItemTileType.EMPTY, "     "
    );
    /**
     * It is a basic string used before each message from the console.
     */
    public static  final String output = CLIConstants.PURPLE_BRIGHT + "> " + CLIConstants.RESET;
    /**
     * An array that contains the characters used to print the clock for the thread.
     */
    public static final char[] clockChars = {'◴', '◷', '◶', '◵'};
    /**
     * Board and Library constants format.
     */
    public static final String BOARD_AND_LIBRARY_MIDDLE_FRAME_FORMAT = CLIConstants.YELLOW_BOLD + CLIConstants.T_RIGHT + CLIConstants.HORIZONTAL_LINE.repeat(5) + CLIConstants.RESET;
    public static final String LIBRARY_BOTTOM_FRAME_FORMAT = CLIConstants.YELLOW_BOLD + CLIConstants.CORNER_BOTTOM_LEFT + CLIConstants.HORIZONTAL_LINE.repeat(5) + CLIConstants.RESET;
    public static final String BOARD_LONG_DISTANCE_SEPARATOR_FRAME_FORMAT = CLIConstants.YELLOW_BOLD + CLIConstants.T_LEFT + " ".repeat(9) + CLIConstants.RESET;
    public static final String BOARD_SHORT_DISTANCE_SEPARATOR_FRAME_FORMAT = CLIConstants.YELLOW_BOLD + CLIConstants.T_LEFT + " ".repeat(6) + CLIConstants.RESET;
    /**
     * Leaderboard constants format.
     */
    public static final String LEADERBOARD_HEADER_FORMAT = CLIConstants.GREEN + CLIConstants.VERTICAL_LINE_DOUBLE + CLIConstants.CYAN + " %-4s " + CLIConstants.GREEN + CLIConstants.VERTICAL_LINE_DOUBLE + CLIConstants.CYAN + " %-20s " + CLIConstants.GREEN + CLIConstants.VERTICAL_LINE_DOUBLE + CLIConstants.CYAN + " %-5s " + CLIConstants.GREEN + CLIConstants.VERTICAL_LINE_DOUBLE + CLIConstants.RESET;
    public static final String LEADERBOARD_TOP_FRAME_FORMAT = CLIConstants.GREEN + CLIConstants.CORNER_TOP_LEFT_DOUBLE + CLIConstants.HORIZONTAL_LINE_DOUBLE.repeat(6) + CLIConstants.T_DOWN_DOUBLE + CLIConstants.HORIZONTAL_LINE_DOUBLE.repeat(22) + CLIConstants.T_DOWN_DOUBLE + CLIConstants.HORIZONTAL_LINE_DOUBLE.repeat(8) + CLIConstants.CORNER_TOP_RIGHT_DOUBLE + CLIConstants.RESET;
    public static final String LEADERBOARD_MIDDLE_FRAME_FORMAT = CLIConstants.GREEN + CLIConstants.T_RIGHT_DOUBLE + CLIConstants.HORIZONTAL_LINE_DOUBLE.repeat(6) + CLIConstants.CROSS_DOUBLE + CLIConstants.HORIZONTAL_LINE_DOUBLE.repeat(22) + CLIConstants.CROSS_DOUBLE + CLIConstants.HORIZONTAL_LINE_DOUBLE.repeat(8) + CLIConstants.T_LEFT_DOUBLE + CLIConstants.RESET;
    public static final String LEADERBOARD_BOTTOM_FRAME_FORMAT = CLIConstants.GREEN + CLIConstants.CORNER_BOTTOM_LEFT_DOUBLE + CLIConstants.HORIZONTAL_LINE_DOUBLE.repeat(6) + CLIConstants.T_UP_DOUBLE + CLIConstants.HORIZONTAL_LINE_DOUBLE.repeat(22) + CLIConstants.T_UP_DOUBLE + CLIConstants.HORIZONTAL_LINE_DOUBLE.repeat(8) + CLIConstants.CORNER_BOTTOM_RIGHT_DOUBLE + CLIConstants.RESET;
    /**
     * Maybe we'll use this shorter description to replace the common goal card description.
     */
    private static final String shortDescription = """
            Six groups, each with at least 2 same-type tiles. Groups may have different tiles.
            Four groups, each with at least 4 same-type tiles. Groups may have different tiles.
            Four same-type tiles in the bookshelf's four corners.
            Two groups, each with a 2x2 square of 4 same-type tiles. Squares may have different tiles.
            Three columns with 6 tiles each, max 3 different types. Columns may have different combinations.
            Eight same-type tiles, no position restrictions.
            Four lines, each with 5 tiles, max 3 different types. Lines may have different combinations.
            Two lines, each with 5 different tile types. Lines may have different combinations.
            Five columns with increasing/decreasing height. Next column has 1 more tile. Any tile type.
            """;
}
