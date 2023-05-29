package it.polimi.ingsw.view.gui;

import javafx.beans.property.SimpleStringProperty;

/**
 * Object that will fill the different table (username+points)
 */
public class  Result {
    /**
     * Player username
     */
    private final SimpleStringProperty userName;
    /**
     * Player points
     */
    private final SimpleStringProperty points;

    /**
     * Constructor for the object Results, that will be called when a new row in Username+Points table is added
     */
    public Result(String userName, String points) {
        this.userName = new SimpleStringProperty(userName);
        this.points = new SimpleStringProperty(points);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public String getPoints() {
        return points.get();
    }

    public SimpleStringProperty pointsProperty() {
        return points;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public void setPoints(String points) {
        this.points.set(points);
    }
}
