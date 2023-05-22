package it.polimi.ingsw.view.gui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class  Result {
    private final SimpleStringProperty userName;
    private final SimpleIntegerProperty points;

    public Result(String userName, Integer points) {
        this.userName = new SimpleStringProperty(userName);
        this.points = new SimpleIntegerProperty(points);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public int getPoints() {
        return points.get();
    }

    public SimpleIntegerProperty pointsProperty() {
        return points;
    }
}
