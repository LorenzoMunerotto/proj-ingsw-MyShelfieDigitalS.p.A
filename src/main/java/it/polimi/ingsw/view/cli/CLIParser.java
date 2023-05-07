package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.gameEntity.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to parse the input from the user.
 */
public class CLIParser {

    /**
     * This array contains the values of the rows of the game board and library.
     */
    public static final int[] CARDINALITY_COLUMN_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    /**
     * This array contains the values of the columns of the game board and library.
     */
    public static final String[] CARDINALITY_ROW_VALUES = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

    /**
     * This method is used to get the value of the column from the key.
     *
     * @param key the key of the column
     * @return the value of the column
     */
    public int getColumnValue(int key) {
        return CARDINALITY_COLUMN_VALUES[key];
    }

    /**
     * This method is used to get the key of the column from the value.
     *
     * @param value the value of the column
     * @return the key of the column
     */
    public int getColumnKey(int value) {
        return  value - 1;
    }

    /**
     * This method is used to get the value of the row from the key.
     *
     * @param key the key of the row
     * @return the value of the row
     */
    public String getRowValue(int key) {
        return CARDINALITY_ROW_VALUES[key];
    }

    /**
     * This method is used to get the key of the row from the value.
     *
     * @param value the value of the row
     * @return the key of the row
     */
    public int getRowKey(String value) {
        for (int i = 0; i < CARDINALITY_ROW_VALUES.length; i++) {
            if (CARDINALITY_ROW_VALUES[i].equals(value)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Value not found");
    }

    /**
     * This method is used to parse the coordinates from the input.
     *
     * @param coordinatesFromInput the coordinates from the input
     * @return the list of the coordinates
     */
    public List<Coordinate> parseCoordinates(String coordinatesFromInput) {
        List<Coordinate> coordinateList = new ArrayList<>();
        String newString;

        newString = coordinatesFromInput.replaceAll(" ", "");
        newString = newString.replaceAll("\n", "");
        newString = newString.replaceAll("\"", "");
        String[] coordinates = newString.split("-");

        for (String coordinate : coordinates) {
            int row = getRowKey(String.valueOf(coordinate.charAt(0)));
            int col = getColumnKey(Integer.parseInt(String.valueOf(coordinate.charAt(1))));

            coordinateList.add(new Coordinate(row, col));
        }
        return coordinateList;
    }
}
