package it.polimi.ingsw.client;

import it.polimi.ingsw.client.clientMessage.ClientMessage;
import it.polimi.ingsw.client.view.cli.CLIConstants;
import it.polimi.ingsw.server.serverMessage.*;

import java.io.*;
import java.net.Socket;

public class SocketListener implements Runnable {

    Client client;
    Socket socketServer;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;


    public SocketListener(Client client) {

        this.client = client;
        try {
            System.out.println("I'm trying to connect to the server");
            socketServer = new Socket("localhost", 1235);
            System.out.println(CLIConstants.GREEN_BRIGHT + "Connection established" + CLIConstants.RESET);

            outputStream = new ObjectOutputStream(socketServer.getOutputStream());
            inputStream = new ObjectInputStream(socketServer.getInputStream());

        } catch (IOException ex) {
            System.err.println("The server is not running");
        }
    }

    public synchronized void readFromStream() throws IOException, ClassNotFoundException {

        ServerMessage input = (ServerMessage) inputStream.readObject();
        System.out.println("--> new message received from server: " + input);
        if (input instanceof NumOfPlayerRequest) {
            client.handle((NumOfPlayerRequest) input);
        } else if (input instanceof CustomMessage) {
            client.handle((CustomMessage) input);
        } else if (input instanceof UsernameRequest) {
            client.handle((UsernameRequest) input);
        } else if (input instanceof BoardUpdateMessage) {
            client.handle((BoardUpdateMessage) input);
        } else if (input instanceof StartTurnMessage) {
            client.handle((StartTurnMessage) input);
        } else if (input instanceof MoveRequest) {
            client.handle((MoveRequest) input);
        } else if (input instanceof LibraryUpdateMessage) {
            client.handle((LibraryUpdateMessage) input);
        } else {
            client.handle(input);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                readFromStream();
            }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
    }

    /**
     * This method use socket to send a message to the server.
     *
     * @param clientMessage is the message that the client want to send
     */
    public void send(ClientMessage clientMessage) {
        try {
            outputStream.reset();
            outputStream.writeObject(clientMessage);
            outputStream.flush();

        } catch (IOException e) {
            System.out.println("Sending message failed" + e.getMessage());
            e.printStackTrace();
        }
    }
}
