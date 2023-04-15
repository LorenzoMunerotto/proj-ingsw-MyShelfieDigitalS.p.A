package it.polimi.ingsw.networking;

import it.polimi.ingsw.model.gameState.GameData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Server server;
    private PrintWriter out;
    private BufferedReader in;
    private String playerName;

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Inserisci il tuo nome utente: ");
            playerName = in.readLine();
            System.out.println("Giocatore " + playerName + " si è unito al gioco.");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Gestisci la comunicazione con il client qui
                System.out.println("Comando ricevuto da " + playerName + ": " + inputLine);
                handleClientMessage(inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    private void handleClientMessage(String message) {
        // Gestisci i messaggi ricevuti dal client qui
        // Ad esempio, puoi inviare i messaggi al server per eseguire azioni specifiche
    }
}
