package it.polimi.ingsw.client;

import it.polimi.ingsw.client.clientMessage.ClientMessage;
import it.polimi.ingsw.view.cli.CLIConstants;
import it.polimi.ingsw.server.serverMessage.*;

import java.io.*;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * This class is the socket listener of the client.
 */
public class SocketListener implements Runnable {

    /**
     * It is the client.
     */
    private Client client;
    /**
     * It is the socket of the server.
     */
    private Socket socketServer;
    /**
     * It is the input stream of the socket.
     */
    private ObjectInputStream inputStream;
    /**
     * It is the output stream of the socket.
     */
    private ObjectOutputStream outputStream;
    /**
     * Lock for send method
     */
    private Object lockSend = new Object();
    private Boolean active;

    /**
     * Default constructor, initialize the client and the socket.
     *
     * @param client   is the client
     * @param serverIp is the ip of the server
     * @param port     is the port of the server
     */
    public SocketListener(Client client, String serverIp, int port) {
        this.client = client;
        try {
            System.out.println("I'm trying to connect to the server...");
            socketServer = new Socket(serverIp, port);
            System.out.println(CLIConstants.GREEN_BRIGHT + "Connection established" + CLIConstants.RESET);
            System.out.println("Please wait while we load the resources...");
            outputStream = new ObjectOutputStream(socketServer.getOutputStream());
            inputStream = new ObjectInputStream(socketServer.getInputStream());

        } catch (IOException ex) {
            System.err.println("The server is not running");
        }
        this.active=true;
    }

    /**
     * This method read a message from the socket and call the accept method of the message.
     *
     * @throws IOException            if there is a problem with the socket
     * @throws ClassNotFoundException if the class of the object received from the socket cannot be found
     */
    public void readFromStream() throws ClassNotFoundException, IOException {
            ServerMessage input = (ServerMessage) inputStream.readObject();
            input.accept(client);
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * This method is the run method of the thread.
     */
    @Override
    public void run() {
        Integer counterEOFExecption =0;
        while (isActive()) {
            try {
                readFromStream();
                counterEOFExecption =0;
            } catch (IOException e) {
                if (!(e instanceof EOFException)) {
                    System.out.println("Lost connection with the server, IOException");
                } else{
                    counterEOFExecption++;
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (counterEOFExecption>=25){
                        setActive(false);
                        client.lostConnection();
                    }
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Lost connection with the server, ClassNotFoundException");
                setActive(false);
            }
        }

            // Close the streams and socket here to make sure they are always closed
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socketServer != null) {
                    socketServer.close();
                }
            } catch (IOException e) {
                System.err.println("Failed to close resources in run method: " + e.getMessage());
                e.printStackTrace();
            }

    }



    /**
     * This method use socket to send a message to the server.
     *
     * @param clientMessage is the message that the client want to send
     */
    public void send(ClientMessage clientMessage) {
        synchronized (lockSend) {
            try {
                outputStream.reset();
                outputStream.writeObject(clientMessage);
                outputStream.flush();

            } catch (IOException e) {
                System.out.println("Sending message failed " + e.getMessage());
                e.printStackTrace();
            }
            lockSend.notifyAll();
        }
    }
}