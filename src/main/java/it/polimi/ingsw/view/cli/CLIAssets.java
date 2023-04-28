package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.Map;

public class CLIAssets {

    public static final String MYSHELFIE_TITLE = ("""
                        
            ███╗   ███╗██╗   ██╗    ███████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗
            ████╗ ████║╚██╗ ██╔╝    ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝
            ██╔████╔██║ ╚████╔╝     ███████╗███████║█████╗  ██║     █████╗  ██║█████╗\s
            ██║╚██╔╝██║  ╚██╔╝      ╚════██║██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝ \s
            ██║ ╚═╝ ██║   ██║       ███████║██║  ██║███████╗███████╗██║     ██║███████╗
            ╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝
                                                                                       """)
            .replaceAll("█", CLIColors.YELLOW_BRIGHT + "█" + CLIColors.RESET)
            .replaceAll("([╔╗║╝═╚])", CLIColors.RED_BRIGHT + "$1" + CLIColors.RESET);


    public static final String USERNAME_REGEX = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    public static final String COORDINATES_REGEX = "([A-I][1-9](-[A-I][1-9]){0,2})";
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
    public static final String PRINT_OBJECTS_TITLE = "                             Board                                              Library                          Personal Card";
    protected static final Map<ItemTileType, String> ITEM_TILES_TYPES_CLI_COLORS = Map.of(
            ItemTileType.CAT, CLIColors.GREEN_BRIGHT + " CAT " + CLIColors.RESET,
            ItemTileType.BOOK, CLIColors.WHITE_BRIGHT + " BOO " + CLIColors.RESET,
            ItemTileType.GAME, CLIColors.YELLOW_BRIGHT + " GAM " + CLIColors.RESET,
            ItemTileType.FRAME, CLIColors.BLUE_BRIGHT + " FRA " + CLIColors.RESET,
            ItemTileType.TROPHY, CLIColors.CYAN_BRIGHT + " TRO " + CLIColors.RESET,
            ItemTileType.PLANT, CLIColors.PURPLE_BRIGHT + " PLA " + CLIColors.RESET,
            ItemTileType.EMPTY, "     "
    );

    public static  final String output = CLIColors.PURPLE_BRIGHT + "> " + CLIColors.RESET;

    public static final char[] clockChars = {'◴', '◷', '◶', '◵'};

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
