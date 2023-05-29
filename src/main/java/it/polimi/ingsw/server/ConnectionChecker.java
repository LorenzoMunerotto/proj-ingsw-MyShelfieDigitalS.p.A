package it.polimi.ingsw.server;

import it.polimi.ingsw.server.serverMessage.CheckConnectionRequest;

public class ConnectionChecker implements Runnable {

    /**
     * The client's SocketClientConnection
     */
    private final SocketClientConnection socketClientConnection;
    /**
     * The server
     */
    private final Server server;
    /**
     * Indicates whether the client is connected
     */
    private Boolean clientIsConnected = false;

    /**
     * Default constructor
     * @param socketClientConnection the socketClientConnection
     * @param server the server
     */
    public ConnectionChecker(SocketClientConnection socketClientConnection, Server server) {
        this.socketClientConnection = socketClientConnection;
        this.server=server;
    }

    /**
     * This method set the clientIsConnected flag
     * @param clientIsConnected clientIsConnected
     */
    public void setClientIsConnected(Boolean clientIsConnected) {
        this.clientIsConnected = clientIsConnected;
    }

    /**
     * This method run the connection Checker
     */
    @Override
    public void run() {

        while (socketClientConnection.isActive()) {
            clientIsConnected=false;
            socketClientConnection.send(new CheckConnectionRequest());
            try {
                Thread.sleep(3000);
            }catch(InterruptedException e){
                System.out.println("sleep connectionChecker interrupted");
                throw new RuntimeException(e);
            }

            if (!clientIsConnected && socketClientConnection.isActive()) {
                System.out.println("Client " + socketClientConnection.getClientID() +" goes offline" );
                server.getGameHandlerByClientId(socketClientConnection.getClientID()).stopGameByClientDisconnection(server.getUsernameByClientId(socketClientConnection.getClientID()));
            }
        }
    }
}
