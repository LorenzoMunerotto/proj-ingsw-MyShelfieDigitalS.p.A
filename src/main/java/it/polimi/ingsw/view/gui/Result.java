package it.polimi.ingsw.view.gui;

import javafx.beans.property.SimpleStringProperty;

/**
 * Object that will fill the different table (username+points)
 */
public class  Result {
    /**
     * Player username
     */
    private final SimpleStringProperty username;
    /**
     * Player points
     */
    private final SimpleStringProperty points;

    /**
     * Constructor for the object Results, that will be called when a new row in Username+Points table is added
     */
    public Result(String username, String points) {
        this.username = new SimpleStringProperty(username);
        this.points = new SimpleStringProperty(points);
    }

    public String getPoints() {
        return points.get();
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setPoints(String points) {
        this.points.set(points);
    }
}
