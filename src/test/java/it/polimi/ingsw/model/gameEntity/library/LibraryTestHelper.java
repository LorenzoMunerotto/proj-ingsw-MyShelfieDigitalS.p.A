package it.polimi.ingsw.model.gameEntity.library;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Helper class for testing the library.
 */
public class LibraryTestHelper extends it.polimi.ingsw.model.gameEntity.Library {

    /**
     * Set the library with the given grid of item tile types.
     * It creates a snapshot of the grid of item tile types.
     * Used only for testing purposes.
     *
     * @param gridOfItemTileType the grid of item tile types
     */
    public void setLibrary(ItemTileType[][] gridOfItemTileType) {
        for (int row = 0; row < getLibraryGrid().length; row++) {
            for (int column = 0; column < getLibraryGrid()[0].length; column++) {
                this.setItemTile(row, column, gridOfItemTileType[row][column]);
            }
        }
    }

    /**
     * Set the library with the given string.
     *
     * @param str the string
     */
    public void setLibraryFromString(String str) {
        String newStr;
        newStr = str.replaceAll("\r\n|\n|\r", "");
        newStr = newStr.replaceAll(" ", "");
        newStr = newStr.replaceAll("\"", "");
        String[] rows = newStr.split("\\|");

        ItemTileType[][] gridType = new ItemTileType[getLibraryGrid().length][getLibraryGrid()[0].length];

        for (int row = 0; row < getLibraryGrid().length; row++) {
            String[] singleRow = rows[row].split(",");
            for (int col = 0; col < getLibraryGrid()[0].length; col++) {
                try {
                    ItemTileType tileType = ItemTileType.valueOf(singleRow[col]);
                    gridType[row][col] = tileType;
                } catch (IllegalArgumentException e) {
                    System.err.printf("Invalid ItemTileType value '%s' at row %d, column %d%n", singleRow[col], row, col);
                }
            }
        }
        setLibrary(gridType);
    }
}
