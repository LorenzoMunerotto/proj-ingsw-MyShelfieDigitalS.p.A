package it.polimi.ingsw.view.gui;

import javafx.beans.property.SimpleStringProperty;

public class  Result {
    private final SimpleStringProperty userName;
    private final SimpleStringProperty points;

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
