
 package it.polimi.ingsw.model.gameMechanics;



import java.io.IOException;


public class GameLogic{


    /**
     * Constructor for game logic, initializes the game data, the board manager and the library manager.
     *
     *
     */
    public GameLogic() {

    }




    /**
     * This method initializes the game.
     * It refills the board and updates it, it generates 2 random common goal cards,
     * and a personal goal cards for each player.
     */
    public void initGame() throws IOException {
        /*
        this.boardManager.refillBoard();
        this.boardManager.updateBoard();
        this.commonGoalCards = CommonCardFactory.createCards(numberOfPlayers);
        /* for(String username : playersUsernames){
            //fix this when personal goal cards are implemented
            this.gameData.getPlayerDashboards().get(username).setPersonalGoalCard(new PersonalGoalCard(numberCardP));
        } */

    }

    public void phase1(){

    }

    public void phase2(){

    }

    /**
     * Class that represents an exception thrown when a game logic error occurs.
     */
    public static class GameLogicException extends Exception {
        /**
         * Constructs a new exception with the specified detail message.
         *
         * @param message the detail message.
         */
        public GameLogicException(String message) {
            super(message);
        }
    }

}
