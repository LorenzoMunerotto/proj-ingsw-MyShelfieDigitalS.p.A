package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameState.Exceptions.GameStartedException;
import it.polimi.ingsw.model.gameState.Exceptions.IllegalUsernameException;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameState.Exceptions.UsernameAlreadyExistsException;
import it.polimi.ingsw.model.gameState.GameData;

public class GameMaster {
    /**
     * GameMaster it's the game's engine starter
     */

    private GameData gameData;


    public GameMaster(GameData gameData)  {
       this.gameData=gameData;
    }


    /*
    public int generateRandomNumber(){
        while (true){
            int random= (int) (Math.random()*(12)+1);
            for(int i=0; i<players.size(); i++){
                if (listOfPersonalCard[i]==0){
                    if(i==1 && listOfPersonalCard[0]!=random){
                        listOfPersonalCard[1]=random;

                        return random;
                    }
                    if(i==2 && listOfPersonalCard[0]!=random && listOfPersonalCard[1]!=random){
                        listOfPersonalCard[2]=random;
                        return random;
                    }
                    if(i==3 && listOfPersonalCard[0]!=random && listOfPersonalCard[1]!=random && listOfPersonalCard[2]!=random){
                        listOfPersonalCard[3]=random;
                        return random;
                    }
                    if(i==0 && listOfPersonalCard[0]==0){
                        listOfPersonalCard[0]=random;
                        return random;
                    };
                }
            }
        }

    } */


    /*
     questo metodo si occupa di gestire la stringa che l'utente immette come nickname a prescindere che sia
     il primo giocatore collegato o pure uno dei successivi
     l'username scelto dall'utente può sollevare due tipi di eccezioni diverse, ma in ogni caso all'utente
     verrà fatto riscegliere l'username spiegando perchè quello precedente non era ammissibile
     se il metodo termina senza sollevare eccezioni il risultatato è aver creato un Player
     con un username corretto e averlo aggiunto alla lista dei player in gameData
     dopo aver aggiunto il player alla lista di player si controlla se si è arrivati al numOfPlayerstabilito
     e in tal caso si setta started in gameState a true

      */
    public void acceptUser(String username)  {

        Player newPlayer = null;
        try {
            newPlayer = new Player(username);
        }catch(IllegalUsernameException e){
            //l'utente ha scelto un username illegale (ad esempio "")
        }

        try{
            gameData.addPlayer(newPlayer);
        }
        catch(UsernameAlreadyExistsException e){
            //l'utente ha scelto un username già esistente
        }
        catch (GameStartedException e) {
           // l'utente ha provato ad accedere ma precedentemente si era già raggiunto il numero di giocatori richiesti e
            // la partita è iniziata
        }


    }


}

