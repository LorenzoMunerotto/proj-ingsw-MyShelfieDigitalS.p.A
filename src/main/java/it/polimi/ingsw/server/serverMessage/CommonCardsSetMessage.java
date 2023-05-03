package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameState.events.CommonCardsSetEvent;

import java.util.List;

public class CommonCardsSetMessage implements ServerMessage{

    private final Integer CommonCard1Index;
    private final Integer CommonCard1PointsAvailable;
    private final String CommonCard1Description;
    private final Integer CommonCard2Index;
    private final Integer CommonCard2PointsAvailable;
    private final String CommonCard2Description;

    public CommonCardsSetMessage(CommonCardsSetEvent commonCardsSetEvent) {
        CommonCard1Index = commonCardsSetEvent.getCommonCard1Index();
        CommonCard1PointsAvailable = commonCardsSetEvent.getCommonCard1PointsAvailable();
        CommonCard1Description = commonCardsSetEvent.getCommonCard1Description();
        CommonCard2Index = commonCardsSetEvent.getCommonCard2Index();
        CommonCard2PointsAvailable = commonCardsSetEvent.getCommonCard2PointsAvailable();
        CommonCard2Description = commonCardsSetEvent.getCommonCard2Description();
    }

    public Integer getCommonCard1Index() {
        return CommonCard1Index;
    }

    public Integer getCommonCard1PointsAvailable() {
        return CommonCard1PointsAvailable;
    }

    public String getCommonCard1Description() {
        return CommonCard1Description;
    }

    public Integer getCommonCard2Index() {
        return CommonCard2Index;
    }

    public Integer getCommonCard2PointsAvailable() {
        return CommonCard2PointsAvailable;
    }

    public String getCommonCard2Description() {
        return CommonCard2Description;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
