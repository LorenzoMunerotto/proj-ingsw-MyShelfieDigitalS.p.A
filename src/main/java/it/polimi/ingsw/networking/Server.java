package it.polimi.ingsw.networking;

import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameState.Exceptions.GameStartedException;
import it.polimi.ingsw.model.gameState.Exceptions.IllegalUsernameException;
import it.polimi.ingsw.model.gameState.Exceptions.UsernameAlreadyExistsException;
import it.polimi.ingsw.model.gameState.GameData;
import it.polimi.ingsw.terminal.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private final int port;
    private final List<ClientHandler> clientHandlers = new ArrayList<>();
    private final GameData gameData;
    private final Controller controller;

    public Server(int port) {
        this.port = port;
        this.gameData = new GameData();
        this.controller = new Controller(gameData);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server avviato sulla porta " + port);

            while (clientHandlers.size() < 2) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connesso: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }

            for (ClientHandler clientHandler : clientHandlers) {
                String playerName = clientHandler.getPlayerName();
                addPlayer(playerName);
            }

            startGame();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageNewUsers() {
        while (gameData.getCurrentNumOfPlayers() < 2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (gameData.getNumOfPlayers() == 0) {
            try {
                System.out.print("Please, select the number of player for this game: ");
                InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader keyboard = new BufferedReader(input);
                int numOfPlayer = Integer.parseInt(keyboard.readLine());
                gameData.setNumOfPlayers(numOfPlayer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (gameData.getCurrentNumOfPlayers() != gameData.getNumOfPlayers()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void addPlayer(String playerName) {
        try {
            Player newPlayer = new Player(playerName);
            gameData.addPlayer(newPlayer);
            System.out.println("Welcome " + playerName + " to the game!");
        } catch (IllegalUsernameException | UsernameAlreadyExistsException | GameStartedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void startGame() {
        manageNewUsers();

        // Avvia il gioco qui
    }

    public static void main(String[] args) {
        int serverPort = 12345;
        Server server = new Server(serverPort);
        server.start();
    }
}