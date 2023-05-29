package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.Map;

/**
 * This class contains all the constants used in the CLI.
 */
public class CLIConstants {
    /**
     * It is the title of the game.
     */
    public static final String MYSHELFIE_TITLE = ("""
            ███╗   ███╗██╗   ██╗    ███████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗
            ████╗ ████║╚██╗ ██╔╝    ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝
            ██╔████╔██║ ╚████╔╝     ███████╗███████║█████╗  ██║     █████╗  ██║█████╗  \s
            ██║╚██╔╝██║  ╚██╔╝      ╚════██║██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝  \s
            ██║ ╚═╝ ██║   ██║       ███████║██║  ██║███████╗███████╗██║     ██║███████╗
            ╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝""")
            .replaceAll("█", CLIConstants.YELLOW + "█" + CLIConstants.RESET)
            .replaceAll("([╔╗║╝═╚╣╩╦])", CLIConstants.RED_BRIGHT + "$1" + CLIConstants.RESET);
    /**
     * Winner drawing.
     */
    public static final String MYSHELFIE_WINNER = ("""
            ░██╗░░░░░░░██╗██╗███╗░░██╗███╗░░██╗███████╗██████╗░
            ░██║░░██╗░░██║██║████╗░██║████╗░██║██╔════╝██╔══██╗
            ░╚██╗████╗██╔╝██║██╔██╗██║██╔██╗██║█████╗░░██████╔╝
            ░░████╔═████║░██║██║╚████║██║╚████║██╔══╝░░██╔══██╗
            ░░╚██╔╝░╚██╔╝░██║██║░╚███║██║░╚███║███████╗██║░░██║
            ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚══╝╚══════╝╚═╝░░╚═╝""")
            .replaceAll("░", CLIConstants.GREEN_BRIGHT + "░" + CLIConstants.RESET)
            .replaceAll("([╔╗║╝═╚╣╩╦])", CLIConstants.YELLOW_BOLD + "$1" + CLIConstants.RESET)
            .replaceAll("█", CLIConstants.RED_BRIGHT + "█" + CLIConstants.RESET);
    /**
     * Looser drawing.
     */
    public static final String MYSHELFIE_LOOSER = ("""
            ███████████████████████████████████
            █▄─▄███─▄▄─█─▄▄─█─▄▄▄▄█▄─▄▄─█▄─▄▄▀█
            ██─██▀█─██─█─██─█▄▄▄▄─██─▄█▀██─▄─▄█
            ▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▀▄▄▀""")
            .replaceAll("█", CLIConstants.BLUE_BOLD + "█" + CLIConstants.RESET)
            .replaceAll("([▄─▀])", CLIConstants.PURPLE_BOLD + "$1" + CLIConstants.RESET);
    /**
     * It is the regex used to check the validity of the username.
     */
    public static final String USERNAME_REGEX = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    /**
     * It is the regex used to check the validity of the coordinates.
     */
    public static final String COORDINATES_REGEX = "([A-I][1-9](-[A-I][1-9]){0,2})";
    /**
     * It is a map that contains the association between the item tile type and the color used to print it.
     * Used for the board, the library and the personal card.
     */
    protected static final Map<ItemTileType, String> ITEM_TILES_TYPES_CLI_COLORS = Map.of(
            ItemTileType.CAT, CLIConstants.BLACK_TEXT_GREEN_BACKGROUND_BRIGHT + " CAT " + CLIConstants.RESET,
            ItemTileType.BOOK, CLIConstants.BLACK_TEXT_WHITE_BACKGROUND_BRIGHT + " BOO " + CLIConstants.RESET,
            ItemTileType.GAME, CLIConstants.BLACK_TEXT_YELLOW_BACKGROUND_BRIGHT + " GAM " + CLIConstants.RESET,
            ItemTileType.FRAME, CLIConstants.BLACK_TEXT_BLUE_BACKGROUND_BRIGHT + " FRA " + CLIConstants.RESET,
            ItemTileType.TROPHY, CLIConstants.BLACK_TEXT_CYAN_BACKGROUND_BRIGHT + " TRO " + CLIConstants.RESET,
            ItemTileType.PLANT, CLIConstants.BLACK_TEXT_PURPLE_BACKGROUND_BRIGHT + " PLA " + CLIConstants.RESET,
            ItemTileType.EMPTY, "     ",
            ItemTileType.NULL,  "     "
    );
    /**
     * It is a map that contains the association between the item tile type and the color used to print it.
     * Used for the small libraries of the other players.
     */
    protected static final Map<ItemTileType, String> ITEM_TILES_TYPES_CLI_COLORS_SMALL = Map.of(
            ItemTileType.CAT, CLIConstants.BLACK_TEXT_GREEN_BACKGROUND_BRIGHT + " C " + CLIConstants.RESET,
            ItemTileType.BOOK, CLIConstants.BLACK_TEXT_WHITE_BACKGROUND_BRIGHT + " B " + CLIConstants.RESET,
            ItemTileType.GAME, CLIConstants.BLACK_TEXT_YELLOW_BACKGROUND_BRIGHT + " G " + CLIConstants.RESET,
            ItemTileType.FRAME, CLIConstants.BLACK_TEXT_BLUE_BACKGROUND_BRIGHT + " F " + CLIConstants.RESET,
            ItemTileType.TROPHY, CLIConstants.BLACK_TEXT_CYAN_BACKGROUND_BRIGHT + " T " + CLIConstants.RESET,
            ItemTileType.PLANT, CLIConstants.BLACK_TEXT_PURPLE_BACKGROUND_BRIGHT + " P " + CLIConstants.RESET,
            ItemTileType.EMPTY, "   ",
            ItemTileType.NULL,  "   "
    );
    /**
     * It is the string used to print the legend of the tiles associated to the colors.
     */
    public static final String ITEM_TILES_TYPE_COLOR_LEGEND = CLIConstants.WHITE_UNDERLINED + "Legend:" + CLIConstants.RESET + "      \n" +
            CLIConstants.GREEN_BACKGROUND_BRIGHT + "   " + CLIConstants.RESET + " = CAT   \n" +
            CLIConstants.WHITE_BACKGROUND_BRIGHT + "   " + CLIConstants.RESET + " = BOOK  \n" +
            CLIConstants.YELLOW_BACKGROUND + "   " + CLIConstants.RESET + " = GAME  \n" +
            CLIConstants.BLUE_BACKGROUND_BRIGHT + "   " + CLIConstants.RESET + " = FRAME \n" +
            CLIConstants.CYAN_BACKGROUND_BRIGHT + "   " + CLIConstants.RESET + " = TROPHY\n" +
            CLIConstants.PURPLE_BACKGROUND_BRIGHT + "   " + CLIConstants.RESET + " = PLANT \n" +
            "            \n" +
            "            \n";
    /**
     * It is a basic string used before each message from the console.
     */
    public static  final String CONSOLE_ARROW = CLIConstants.PURPLE_BRIGHT + "> " + CLIConstants.RESET;
    /**
     * An array that contains the characters used to print the clock for the thread.
     */
    public static final char[] LOADING_ANIMATIONS = {'▖', '▘', '▝', '▗'};
    /**
     * Used to reset the color of the text.
     */
    public static final String RESET = "\033[0m";
    /**
     * A list of all the possible colors.
     * Once we choose which colors to use, we can delete the unused ones.
     */
    public static final String BLACK_TEXT_GREEN_BACKGROUND_BRIGHT = "\u001B[30;102m";
    public static final String BLACK_TEXT_WHITE_BACKGROUND_BRIGHT = "\u001B[30;107m";
    public static final String BLACK_TEXT_YELLOW_BACKGROUND_BRIGHT = "\u001B[30;43m";
    public static final String BLACK_TEXT_BLUE_BACKGROUND_BRIGHT = "\u001B[30;104m";
    public static final String BLACK_TEXT_CYAN_BACKGROUND_BRIGHT = "\u001B[30;106m";
    public static final String BLACK_TEXT_PURPLE_BACKGROUND_BRIGHT = "\u001B[30;105m";
    public static final String YELLOW = "\033[0;33m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String WHITE_UNDERLINED = "\033[4;37m";
    public static final String YELLOW_BACKGROUND = "\033[43m";
    public static final String RED_BRIGHT = "\033[0;91m";
    public static final String GREEN_BRIGHT = "\033[0;92m";
    public static final String YELLOW_BRIGHT = "\033[0;93m";
    public static final String BLUE_BRIGHT = "\033[0;94m";
    public static final String PURPLE_BRIGHT = "\033[0;95m";
    public static final String CYAN_BRIGHT = "\033[0;96m";
    public static final String WHITE_BRIGHT = "\033[0;97m";
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m";
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";
    /**
     * A list of all the used symbols in the drawer.
     */
    public static final String CORNER_TOP_LEFT = "┌";
    public static final String CORNER_TOP_RIGHT = "┐";
    public static final String CORNER_BOTTOM_LEFT = "└";
    public static final String CORNER_BOTTOM_RIGHT = "┘";
    public static final String HORIZONTAL_LINE = "─";
    public static final String VERTICAL_LINE = "│";
    public static final String T_DOWN = "┬";
    public static final String T_UP = "┴";
    public static final String T_LEFT = "┤";
    public static final String T_RIGHT = "├";
    public static final String CROSS = "┼";
}