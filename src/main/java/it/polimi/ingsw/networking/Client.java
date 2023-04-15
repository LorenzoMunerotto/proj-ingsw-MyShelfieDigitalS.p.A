package it.polimi.ingsw.networking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final String serverAddress;
    private final int serverPort;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void start() {
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connesso al server: " + socket.getInetAddress());

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread serverListener = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("Server: " + serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverListener.start();

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                out.println(userMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;

        Client client1 = new Client(serverAddress, serverPort);
        client1.start();
    }
}
