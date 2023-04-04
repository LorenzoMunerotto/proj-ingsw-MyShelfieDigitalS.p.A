package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;

import java.util.ArrayList;
import java.util.List;


public abstract class CommonGoalCard {

    private Integer index;
    //private String description;
    private List<String> smartPlayerTail;

    public CommonGoalCard(Integer index) {
        this.index = index;
        this.smartPlayerTail =  new ArrayList<>();
    }

    public void addSmartPlayer(String username){
        if(!isSmartPlayer(username)) {
            smartPlayerTail.add(username);
        }
    }

    public boolean isSmartPlayer(String username){
        return smartPlayerTail.indexOf(username)!=-1;
    }

    public int getPoint(List<Integer> pointsSource, String username){
        int point = pointsSource.get(smartPlayerTail.indexOf(username));
        return point;
    }

    public Integer getIndex()  {return index;}

    public abstract boolean checkRules(Library library);
}
