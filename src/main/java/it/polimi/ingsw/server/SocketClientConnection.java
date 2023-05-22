package it.polimi.ingsw.server;

import it.polimi.ingsw.client.clientMessage.*;
import it.polimi.ingsw.model.gameState.exceptions.IllegalNumOfPlayersException;
import it.polimi.ingsw.server.serverMessage.*;
import it.polimi.ingsw.view.events.Move;
import it.polimi.ingsw.view.events.NumOfPlayerChoice;
import it.polimi.ingsw.view.events.UsernameChoice;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * This class manage the client socket.
 */
public class SocketClientConnection implements ClientMessageHandler, Runnable {

    /**
     * The socket.
     */
    private final Socket socket;
    /**
     * The server.
     */
    private final Server server;
    /**
     * The client ID.
     */
    private Integer clientID;
    /**
     * The input stream.
     */
    private ObjectInputStream inputStream;
    /**
     * The output stream.
     */
    private ObjectOutputStream outputStream;
    /**
     * The boolean that indicates if the client is active.
     */
    private boolean active =true;
    /**
     * Lock for send method
     */
    private final Object lockSend = new Object();
    /**
     * The ExcutorService
     */
    private final ExecutorService executorService;
    /**
     * The ConnectionChecker
     */
    private final ConnectionChecker connectionChecker;
    /**
     * This is the constructor of the class.
     *
     * @param socket the socket
     * @param server the server
     */
    public SocketClientConnection(Socket socket, Server server) {

        this.socket = socket;
        this.server = server;
        this.executorService = Executors.newCachedThreadPool();
        this.connectionChecker = new ConnectionChecker(this, server);
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            clientID = -1;
            setActive(true);
        } catch (IOException e) {
            System.err.println("Error during initialization of the client!");
            System.err.println(e.getMessage());
        }

        try {
            sleep(4000);
        }  catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        send(new UsernameRequest());


    }

    /**
     * This method reads the inputStream of the socket according to the type of message handle.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void readFromStream() throws ClassNotFoundException, IOException {
            ClientMessage input = (ClientMessage) inputStream.readObject();
            new Thread(()->{
                input.accept(this);
            }).start();
    }

    @Override
    public void handle(CheckConnection checkConnection) {
        connectionChecker.setClientIsConnected(true);
    }

    /**
     * This method returns the boolean that indicates if the server is active.
     *
     * @return the boolean that indicates if the server is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * This method sets the boolean that indicates if the server is active.
     *
     * @param active the boolean that indicates if the server is active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * This method send a message to the client writing it on the outputStream of the socket.
     *
     * @param serverMessage the server message
     */
    public  void send(ServerMessage serverMessage) {
        synchronized (lockSend) {
            if (isActive()) {
                try {
                    outputStream.reset();
                    outputStream.writeObject(serverMessage);
                    outputStream.flush();
                } catch (IOException e) {
                    System.out.println("Send failed on client " + clientID + " socket " + serverMessage);
                    server.getGameHandlerByClientId(clientID).stopGameByClientDisconnection(server.getUsernameByClientId(clientID));
                }
            } else {
                System.out.println("Send failed because socketClientConnection is NOT ACTIVE " + serverMessage);
            }
            lockSend.notifyAll();
        }
    }

    /**
     * This method handles the username choice.
     *
     * @param usernameChoice the username choice
     */
    @Override
    public void handle(UsernameChoice usernameChoice) {

            clientID = server.registerConnection(usernameChoice.getUsername(), this);
            if (clientID == null) {
                send(new UsernameRequest());
            } else {
                server.lobby(this);
                executorService.submit(connectionChecker);
            }

    }


    /**
     * This method closes the socket.
     */
    public void close() {
        setActive(false);
        server.unregisterClient(this.clientID);
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * This method handles the move.
     *
     * @param move the move
     */
    @Override
    public void handle(Move move) {
        server.getGameHandlerByClientId(clientID).handle(move);
    }

    /**
     * This method handles the number of players choice.
     *
     * @param numOfPlayerChoice the number of players choice
     */
    @Override
    public void handle(NumOfPlayerChoice numOfPlayerChoice) {
        try {
            int num = numOfPlayerChoice.getNumOfPlayer();
            server.getGameHandlerByClientId(clientID).setNumberOfPlayers(num);
            server.setNumOfPlayers(num);
            send(new CustomMessage("Number of players correctly set to: " + num));
        } catch (IllegalNumOfPlayersException e) {
            send(new ErrorMessage(GameCreationErrors.ILLEGAL_NUM_OF_PLAYER));
            send(new NumOfPlayerRequest());
        }
    }

    public Integer getClientID() {
        return clientID;
    }

    /**
     * This method runs the thread when the client connects.
     * The server reads in loop the socket.
     */
    @Override
    public void run() {
        while (isActive()) {
            try {
                readFromStream();
            } catch (IOException e) {
                if (!(e instanceof EOFException)) {
                    System.out.println("Read failed on client " + clientID + " socket ");
                    server.getGameHandlerByClientId(clientID).stopGameByClientDisconnection(server.getUsernameByClientId(clientID));
                }
            } catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException");
                setActive(false);
            }
        }
    }
}