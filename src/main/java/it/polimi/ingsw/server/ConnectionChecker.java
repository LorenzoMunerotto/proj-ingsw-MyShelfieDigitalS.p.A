package it.polimi.ingsw.server;

import it.polimi.ingsw.client.SocketListener;
import it.polimi.ingsw.server.serverMessage.CheckConnectionRequest;

public class ConnectionChecker implements Runnable {

    private final SocketClientConnection socketClientConnection;
    private final Server server;
    private Boolean clientIsConnected = false;

    public ConnectionChecker(SocketClientConnection socketClientConnection, Server server) {
        this.socketClientConnection = socketClientConnection;
        this.server=server;
    }

    public Boolean getClientIsConnected() {
        return clientIsConnected;
    }

    public void setClientIsConnected(Boolean clientIsConnected) {
        this.clientIsConnected = clientIsConnected;
    }

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
