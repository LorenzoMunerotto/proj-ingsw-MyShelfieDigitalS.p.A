package it.polimi.ingsw.view.events;

import it.polimi.ingsw.client.clientMessage.ClientMessage;
import it.polimi.ingsw.client.clientMessage.ClientMessageHandler;
import it.polimi.ingsw.model.gameEntity.Coordinate;

import java.util.List;

/**
 * This class represents the player's choices about the move.
 */
public class Move implements ClientMessage, ViewEvent {

    /**
     * The list of Board's coordinate selected by the player.
     */
    private final List<Coordinate> coordinateList;
    /**
     * The column chose by the player.
     */
    private final int column;

    /**
     * The constructor of the class.
     *
     * @param coordinateList The list of Board's coordinate selected by the player
     * @param column         The column chose by the player
     */
    public Move(List<Coordinate> coordinateList, int column) {
        this.coordinateList = coordinateList;
        this.column = column;
    }

    /**
     * Get the list of Board's coordinate selected by the player.
     *
     * @return the list of Board's coordinate selected by the player
     */
    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    /**
     * Get the column chose by the player.
     *
     * @return the column chose by the player
     */
    public int getColumn() {
        return column;
    }

    /**
     * Get the message.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void accept(ViewChangeEventHandler viewChangeEventHandler) {
        viewChangeEventHandler.handle(this);
    }

    @Override
    public void accept(ClientMessageHandler clientMessageHandler) {
        clientMessageHandler.handle(this);
    }
}