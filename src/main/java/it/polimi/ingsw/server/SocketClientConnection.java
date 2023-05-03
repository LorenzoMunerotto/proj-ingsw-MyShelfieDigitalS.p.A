package it.polimi.ingsw.server;

import it.polimi.ingsw.client.clientMessage.*;
import it.polimi.ingsw.model.gameState.exceptions.IllegalNumOfPlayersException;
import it.polimi.ingsw.server.serverMessage.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class manage the client socket.
 */
public class SocketClientConnection implements ClientMessageHandler, Runnable {

    private final Socket socket;
    private final Server server;
    private Integer clientID;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private boolean active;


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
     * this method reads the inputStream of the socket and
     * according to the type of message handle.
     * @throws IOException
     * @throws ClassNotFoundException
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

    public boolean isActive() {
        return active;
    }

    /**
     * this method send a message to the client
     * writing it on the outputStream of the socket
     * @param serverMessage
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

    public void setUpNumOfPlayers(){
        send(new NumOfPlayerRequest());
        while(true){
            try {
                ClientMessage input = (ClientMessage) inputStream.readObject();

                if (input instanceof NumOfPlayerChoice){

                    try {
                        int num = (((NumOfPlayerChoice) input).getNumOfPlayer());
                        server.getGameHandlerByClientId(clientID).setNumOfPlayers(num);
                        server.setNumOfPlayers(num);
                        send(new CustomMessage("Num of player correctly set to: " + num));
                        break;
                    } catch (IllegalNumOfPlayersException e) {
                        send(new ErrorMessage(Error.ILLEGAL_NUM_OF_PLAYER));
                        setUpNumOfPlayers();
                    }
                }

            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }


        }

    }


    public void close() {
        server.unregisterClient(this.clientID);
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void handle(Move move) {

        server.getGameHandlerByClientId(clientID).handle(move);
    }

    @Override
    public void handle(NumOfPlayerChoice numOfPlayerChoice) {

            server.setNumOfPlayers(numOfPlayerChoice.getNumOfPlayer());

    }

    /**
     * this thread start when the client connect.
     * Server read in loop the socket
     */
    @Override
    public void run() {
        try{
            while(isActive()){
                readFromStream();
            }

        }catch (IOException e){
            // if it doesn't read, the client has disconnected
            // we have to kill the entire game
        }
        catch (ClassNotFoundException e){

        }
    }


}
