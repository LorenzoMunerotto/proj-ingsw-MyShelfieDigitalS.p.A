package it.polimi.ingsw.terminal;

import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameState.Exceptions.GameStartedException;
import it.polimi.ingsw.model.gameState.Exceptions.IllegalUsernameException;
import it.polimi.ingsw.model.gameState.Exceptions.UsernameAlreadyExistsException;
import it.polimi.ingsw.model.gameState.GameData;
import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    GameData gameData;

    public Controller(GameData gameData) {
        this.gameData = gameData;
    }

    public void manageNewUsers() {

        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader tastiera = new BufferedReader(input);



        while(gameData.getCurrentNumOfPlayers()==0) {
            try {
                System.out.print("1st player, select username: ");
                String username = tastiera.readLine();
                Player newPlayer = new Player(username);
                gameData.addPlayer(newPlayer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while(gameData.getNumOfPlayers()==0) {
            try {
                System.out.print("1st player, select numberOfPlayer: ");
                Integer numOfPlayer = Integer.valueOf(tastiera.readLine());
                gameData.setNumOfPlayers(numOfPlayer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        while (gameData.getCurrentNumOfPlayers() != gameData.getNumOfPlayers()) {
            try {
                System.out.print("new player " + (gameData.getCurrentNumOfPlayers() + 1) + "/" + gameData.getNumOfPlayers() + " select username: ");
                String username = tastiera.readLine();
                Player newPlayer = new Player(username);
                gameData.addPlayer(newPlayer);
            } catch (Exception e) {
            }
        }
    }


    public Pair<List<Pair<Integer, Integer>>,Integer> takePlayerInput() {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader tastiera = new BufferedReader(input);


        List<Pair<Integer, Integer>> listOfCoordinate = new ArrayList<>();
        Integer column  = null;

            System.out.println("inserire coordinate delle tessere desiderate nell'ordine di inserimento e la colonna dove inserirle  ");
            System.out.println("es. 74-75|2");
            try {
                String comandi = tastiera.readLine();

                //parsare i comandi
                String newstr;
                newstr = comandi.replaceAll(" ", "");
                newstr = newstr.replaceAll("\n", "");
                newstr = newstr.replaceAll("\"", "");
                String[] new_comandi = newstr.split("\\|");

                String[] coordinate = new_comandi[0].split("-");

                column = Integer.valueOf(new_comandi[1]);

                for (String coordinata : coordinate) {

                    int row = Integer.valueOf("" + coordinata.charAt(0));
                    int col = Integer.valueOf("" + coordinata.charAt(1));

                    Pair pair = new Pair<>(row, col);
                    listOfCoordinate.add(pair);
                }

            }
            catch (Exception e) {

                throw new IllegalArgumentException("l'input inserito non è accettabile");

            }

            return new Pair<>(listOfCoordinate,column);



    }




}
