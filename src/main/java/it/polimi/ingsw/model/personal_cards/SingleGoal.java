package it.polimi.ingsw.model.personal_cards;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.model.enums.ItemTileType;

public class SingleGoal {

    private int row;
    private int column;
    private ItemTileType itemTileType;

    public SingleGoal(int row, int column, ItemTileType itemTileType) {
        this.row = row;
        this.column = column;
        this.itemTileType = itemTileType;
    }
    private final static String pathFile = "configCard2.json";
    final ObjectMapper objectMapper = new ObjectMapper();
    SingleGoal[] card;

    {
        try {
            card = objectMapper.readValue(pathFile, SingleGoal[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ItemTileType getItemTileType() {
        return itemTileType;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setItemTileType(ItemTileType itemTileType) {
        this.itemTileType = itemTileType;
    }
}
