package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.model.gameState.Exceptions.IllegalNumOfPlayersException;
import it.polimi.ingsw.server.serverMessage.CustomMessage;
import it.polimi.ingsw.server.serverMessage.Error;
import it.polimi.ingsw.server.serverMessage.ErrorMessage;
import it.polimi.ingsw.server.serverMessage.UsernameRequest;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private SocketServer socketServer;

    private Integer nextClientId;
    private GameHandler currentGameHandler;
    private List<SocketClientConnection> waiting;

    private Integer numOfPlayers;

    private Map<Integer, VirtualClient> ClientIdMapVirtualClient;
    private Map<Integer, String> ClientIdMapUsername;
    private Map<String, Integer> UsernameMapClientID;

    private Map<VirtualClient, SocketClientConnection> VirtualClientMapSocketClientConnection;



    public Server()  {
        this.socketServer= new SocketServer(1235, this);
        nextClientId=1;
        ClientIdMapVirtualClient = new HashMap<>();
        ClientIdMapUsername = new HashMap<>();
        UsernameMapClientID = new HashMap<>();
        VirtualClientMapSocketClientConnection = new HashMap<>();
        waiting= new ArrayList<>();

    }


    public synchronized SocketServer getSocketServer(){
        return socketServer;
    }

    public GameHandler getGameHandlerByClientId(int clientId){
        return ClientIdMapVirtualClient.get(clientId).getGameHandler();
    }

    public VirtualClient getVirtualClientByClientId(int clientId){
        return ClientIdMapVirtualClient.get(clientId);
    }

    public String getUsernameByClientId(Integer clientId){
        return ClientIdMapUsername.get(clientId);
    }

    public Integer getClientIdByUsername(String username){
        return UsernameMapClientID.get(username);
    }


    public void setNumOfPlayers(Integer numOfPlayers)  {

            this.numOfPlayers = numOfPlayers;

    }

    public Integer assignClientId(){
        Integer clientId = nextClientId;
        nextClientId++;

        return clientId;
    }


    /**
     * this method register the client on the server based on
     * the UsernameChoice
     * @param username
     * @param socketClientConnection
     * @return
     */
    public synchronized Integer registerConnection(String username,SocketClientConnection socketClientConnection){

        if (waiting.isEmpty()){
            currentGameHandler= new GameHandler();
        }
        if(username == null || username.trim().isEmpty()){
            socketClientConnection.send(new ErrorMessage(Error.ILLEGAL_USERNAME));
            return null;
        }
        if(UsernameMapClientID.keySet().stream().anyMatch(username::equalsIgnoreCase)){
            socketClientConnection.send(new ErrorMessage(Error.DUPLICATE_USERNAME));
            return null;
        }


        //dopo i controlli sull' username, se vanno a buon fine si assegna il clientId
        Integer clientId = assignClientId();
        //creo il virtualClient
        VirtualClient virtualClient = new VirtualClient(socketClientConnection, username, clientId, currentGameHandler);

        currentGameHandler.addVirtualClient(virtualClient);
        currentGameHandler.addPlayer(username, clientId);
        ClientIdMapVirtualClient.put(clientId, virtualClient);
        UsernameMapClientID.put(username, clientId);
        ClientIdMapUsername.put(clientId, username);
        VirtualClientMapSocketClientConnection.put(virtualClient, socketClientConnection);
        System.out.println(virtualClient.getUsername()+ " connected with clientId: " + virtualClient.getClientID());

        return clientId;
    }

    /**
     * This method manage the waiting list in order to start the game
     * when the number of players is reached.
     * @param socketClientConnection
     * @throws InterruptedException
     */
    public synchronized void lobby(SocketClientConnection socketClientConnection) throws InterruptedException{

        waiting.add(socketClientConnection);
        if (waiting.size()==1){
            socketClientConnection.setUpNumOfPlayers();
        }
        else if (waiting.size()==numOfPlayers){
            currentGameHandler.sendAll(new CustomMessage("Number of players reached!"));
            waiting.clear();
            currentGameHandler.startGame();

        }
        else {
            currentGameHandler.sendAll(new CustomMessage(numOfPlayers - waiting.size() + " slot left"));
        }

    }

    public synchronized void unregisterClient(int clientID) {
       // getGameHandlerByClientId(clientID).unregisterPlayer(clientID);
        VirtualClient client = ClientIdMapVirtualClient.get(clientID);
        System.out.println("Unregistering client " + client.getUsername() + "...");
        ClientIdMapVirtualClient.remove(clientID);
        UsernameMapClientID.remove(client.getUsername());
        waiting.remove(VirtualClientMapSocketClientConnection.get(client));
        ClientIdMapUsername.remove(client.getClientID());
        VirtualClientMapSocketClientConnection.remove(client);
        System.out.println("Client has been successfully unregistered.");
    }


    public static void main(String[] args){

        System.out.println("MY SHELFIE SERVER");



        Server server = new Server();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(server.socketServer);
    }

}
