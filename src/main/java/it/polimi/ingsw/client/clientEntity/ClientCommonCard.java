package it.polimi.ingsw.client.clientEntity;

public class ClientCommonCard {

    private final Integer index;
    private Integer currentPoints;
    private final String description;
    private String imgPath;

    public ClientCommonCard(Integer index, Integer currentPoints, String description) {
        this.index = index;
        this.currentPoints = currentPoints;
        this.description = description;
    }

    public Integer getIndex() {
        return index;
    }

    public Integer getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(Integer currentPoints) {
        this.currentPoints = currentPoints;
    }

    public String getDescription() {
        return description;
    }


    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

}
