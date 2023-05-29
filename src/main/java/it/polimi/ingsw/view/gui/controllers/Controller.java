package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.view.gui.GUI;

/**
 * Interface for the different JavaFx Controllers.
 * [A controller is simply a class name whose object
 * is created by FXML and used to initialize the UI elements.
 * all the variables "@FXML" are related to FXML objects with their "ID"]
 */
public interface Controller  {
    /**
     * allow the controller to have Gui
     * @param gui is the main class for the graphics part of the game.
     */
    void setGui(GUI gui);

    /**
     * Work as the "initialize" method,
     * it sets all the initial value for the javafx elements.
     */
    void setUp();
}