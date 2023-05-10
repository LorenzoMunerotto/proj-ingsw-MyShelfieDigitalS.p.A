package it.polimi.ingsw.server;

import it.polimi.ingsw.client.clientMessage.*;
import it.polimi.ingsw.model.gameState.exceptions.IllegalNumOfPlayersException;
import it.polimi.ingsw.server.serverMessage.*;
import it.polimi.ingsw.view.cli.CLIConstants;
import it.polimi.ingsw.view.events.Move;
import it.polimi.ingsw.view.events.NumOfPlayerChoice;
import it.polimi.ingsw.view.events.UsernameChoice;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
    private boolean active;

    /**
     * This is the constructor of the class.
     *
     * @param socket the socket
     * @param server the server
     */
    public SocketClientConnection(Socket socket, Server server) {

        this.socket = socket;
        this.server = server;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            clientID = -1;
            active = true;
        } catch (IOException e) {
            System.err.println( "Error during initialization of the client!");
            System.err.println(e.getMessage());
        }

        try {
            outputStream.reset();
            outputStream.writeObject(new UsernameRequest());
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("Send failed");
        }

    }

    /**
     * This method reads the inputStream of the socket according to the type of message handle.
     *
     * @throws IOException           the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public synchronized void readFromStream() throws IOException, ClassNotFoundException {

        ClientMessage input = (ClientMessage) inputStream.readObject();

        if (input instanceof Move){
            handle((Move) input);
        }
        else if (input instanceof UsernameChoice){
            handle((UsernameChoice) input);
        }
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
     * This method returns the boolean that indicates if the server is active.
     *
     * @return the boolean that indicates if the server is active
     */
    private boolean isActive() {
        return active;
    }

    /**
     * This method send a message to the client writing it on the outputStream of the socket.
     *
     * @param serverMessage the server message
     */
    public void send(ServerMessage serverMessage){
        try {
            outputStream.reset();
            outputStream.writeObject(serverMessage);
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("Send failed");
        }
    }

    /**
     * This method handles the username choice.
     *
     * @param usernameChoice the username choice
     */
    @Override
    public void handle(UsernameChoice usernameChoice) {

        try {
            clientID = server.registerConnection(usernameChoice.getUsername(), this);
            if (clientID == null) {
                send(new UsernameRequest());
            }else{
                server.lobby(this);
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * This method sets up the number of players.
     */
    public void setUpNumOfPlayers(){
        send(new NumOfPlayerRequest());
        while(true){
            try {
                ClientMessage input = (ClientMessage) inputStream.readObject();

                if (input instanceof NumOfPlayerChoice){
                    try {
                        int num = (((NumOfPlayerChoice) input).getNumOfPlayer());
                        server.getGameHandlerByClientId(clientID).setNumberOfPlayers(num);
                        server.setNumOfPlayers(num);
                        send(new CustomMessage("Number of players correctly set to: " + CLIConstants.CYAN_BRIGHT + num + CLIConstants.RESET));
                        break;
                    } catch (IllegalNumOfPlayersException e) {
                        send(new ErrorMessage(GameCreationErrors.ILLEGAL_NUM_OF_PLAYER));
                        setUpNumOfPlayers();
                    }
                }
            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * This method closes the socket.
     */
    public void close() {
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
        server.setNumOfPlayers(numOfPlayerChoice.getNumOfPlayer());
    }

    /**
     * This method runs the thread when the client connects.
     * The server reads in loop the socket.
     */
    @Override
    public void run() {
        try {
            while (isActive()) {
                readFromStream();
            }
        } catch (IOException e) {
            // if it doesn't read, the client has disconnected
            // we have to kill the entire game
            server.getGameHandlerByClientId(clientID).stopGameByClientDisconnection(server.getUsernameByClientId(clientID));
            setActive(false);
        } catch (ClassNotFoundException e) {
            setActive(false);
        } finally {
            close();
        }
    }
}
